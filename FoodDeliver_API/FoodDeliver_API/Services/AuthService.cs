using FoodDeliver_API.Models;
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
        private readonly ApplicationDbContext _context;
        public AuthService(IConfiguration configuration, ApplicationDbContext context)
        {
            _context = context;
            _configuration = configuration;
          
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
            //var existingUser = await _context.Accounts!.FirstOrDefaultAsync(a => a.Email.Equals(user.Email));
            //if (existingUser == null)
            //{
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
            //else
            //{
            //    // Returns a message or value if the user already exists
            //    throw new InvalidOperationException("User already exists.");
            //}
        }


    
}
