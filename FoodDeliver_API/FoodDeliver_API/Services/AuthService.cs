using FoodDeliver_API.Entities;
using FoodDeliver_API.ViewModel.Auth;
using Microsoft.EntityFrameworkCore;
using Microsoft.IdentityModel.Tokens;
using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using System.Text;

namespace FoodDeliver_API.Services
{
    public class AuthService
    {

        private readonly IConfiguration _configuration;
        private readonly EmailService emailService;
        private readonly ApplicationDbContext _context;
        public AuthService(IConfiguration configuration, ApplicationDbContext context, EmailService emailService)
        {
            _context = context;
            _configuration = configuration;
            this.emailService = emailService;
        }

        public async Task<string> LoginAsync(Account acc)
        {


            var isuser = await _context.Accounts!.FirstOrDefaultAsync(u => u.Email == acc.Email);


            if (isuser == null)
            {
                return "";
            }

            // Kiểm tra mật khẩu bằng cách sử dụng BCrypt
            if (BCrypt.Net.BCrypt.Verify(acc.Pass, isuser.Pass))
            {
                return await CreateToken(isuser); // Mật khẩu chính xác, trả về người dùng
            }



            return null;
        }
        private async Task<string> CreateToken(Account? acc)
        {

            if (!acc.Status) return "Inactive";

            //add email to claim
            var authClaims = new List<Claim>
            {
                new(ClaimTypes.Email, acc.Email),
                new("userId", acc.Id.ToString()),
                new("username",acc.Name),
                new(JwtRegisteredClaimNames.Jti, Guid.NewGuid().ToString())
            };

            authClaims.Add(new Claim(ClaimTypes.Role, acc.Role));



            var authenKey = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(_configuration["JWT:Secret"]!));

            var jwtService = new JwtService(_configuration["JWT:Secret"]!, _configuration["JWT:ValidIssuer"]!);
            ////Create token
            var token = jwtService.GenerateTokenLogin(authClaims);



            return token;
        }


        public async Task<string> CreateNewAccount(Account user)
        {

            // Check if the user already exists
            var existingUser = await _context.Accounts!.FirstOrDefaultAsync(a => a.Email.Equals(user.Email));
            if (existingUser == null)
            {
                // Set new user information
                user.Id = new Guid();
                user.Status = true;
                user.Pass = BCrypt.Net.BCrypt.HashPassword(user.Pass);

                user.Role = "User";

                _context.Accounts.Add(user);
                await _context.SaveChangesAsync();



                // Create and gen token
                return await CreateToken(user);
            }
            else
            {
                // Returns a message or value if the user already exists
                throw new InvalidOperationException("User already exists.");
            }
        }

        public async Task ChangePasswordAsync(string token, string newPassword, string oldPassword)
        {

            try
            {
                //get email in token
                var jwtService = new JwtService(_configuration["JWT:Secret"]!, _configuration["JWT:ValidIssuer"]!);

                if (JwtService.IsTokenExpired(token))
                {
                    throw new Exception("Token expired");
                }
                var principal = jwtService.GetPrincipal(token) ?? throw new Exception("Invalid token");
                var emailClaim = principal.FindFirst(ClaimTypes.Email) ?? throw new Exception("Email claim not found in token");

                var email = emailClaim.Value;


                //get user by email
                Account acc = await _context.Accounts!.FirstOrDefaultAsync(u => u.Email == email);

                if (acc == null) throw new Exception("User not found");

                //Check oldpass 
                acc.Pass = BCrypt.Net.BCrypt.Verify(oldPassword, acc.Pass) ? newPassword
                    ?? throw new ArgumentNullException(nameof(newPassword), "New password cannot be null.")
                    : throw new UnauthorizedAccessException("Old password is incorrect.");


                //Change pass

                acc.Pass = BCrypt.Net.BCrypt.HashPassword(newPassword);
              
                await _context.SaveChangesAsync();
            

            }
            catch (Exception e)
            {
                throw new Exception(e.Message);
            }
        }
        public async Task<bool> IsUser(string email)
        {
            var user = await _context.Accounts!.FirstOrDefaultAsync(u => u.Email == email);
            return user != null;
        }

        public async Task SendResetPasswordEmail(string useremail)
        {
            var newPassword = GenerateRandomPassword();

         

            var email = new EmailDto
            {
                To = useremail,
                Subject = "Password Reset",
                Body = $"We have just received a password reset request for {useremail}.<br><br>" +
               $"Your new Password is : {newPassword}<br><br>" +
               $"For your security, Please Change the password!."
            };

            emailService.SendEmail(email);

            //change pass

            Account? user = await _context.Accounts!.FirstOrDefaultAsync(u => u.Email == useremail);
            if (user == null) throw new Exception("User not found");
           
            user.Pass = BCrypt.Net.BCrypt.HashPassword(newPassword);
            await _context.SaveChangesAsync();
        }


        private string GenerateRandomPassword()
        {
            // Độ dài của mật khẩu ngẫu nhiên
            int length = 8;

            // Các ký tự được phép trong mật khẩu
            string validChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()_+=-";

            // Sinh mật khẩu ngẫu nhiên
            Random random = new Random();
            return new string(Enumerable.Repeat(validChars, length)
              .Select(s => s[random.Next(s.Length)]).ToArray());
        }

    }


}
