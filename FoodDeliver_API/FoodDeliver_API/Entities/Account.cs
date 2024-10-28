using System.ComponentModel.DataAnnotations.Schema;

namespace FoodDeliver_API.Models
{
    public class Account
    {
        public Guid Id { get; set; }
        public string Name { get; set; }
        public string Email { get; set; }
        public string Pass { get; set; }
        public string? Img { get; set; }
        public string? Phone { get; set; }
        public string? Address { get; set; }
        public string Role { get; set; }
        public bool Status { get; set; }

        // Navigation property
       public ICollection<Food>? Foods { get; set; } = new List<Food>();
        public ICollection<Order>? Orders { get; set; } = new List<Order>();
        public ICollection<Comment>? Comments { get; set; } = new List<Comment>();
    }
}
