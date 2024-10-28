namespace FoodDeliver_API.ViewModel.Auth
{
    public class ChangePasswordModel
    {
        public string Token { get; set; }
        public string OldPassword { get; set; }
        public string NewPassword { get; set; }
    }
}
