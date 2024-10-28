using System.ComponentModel.DataAnnotations.Schema;

namespace FoodDeliver_API.Models
{
    public class Comment
    {
        public Guid Id { get; set; }
        public string Content { get; set; }
        public int Vote { get; set; }

        // Foreign Keys
        public Guid UserID { get; set; }
        public Guid FoodID { get; set; }

        // Navigation properties
        [ForeignKey("UserID")] public Account? User { get; set; }
        [ForeignKey("FoodID")] public Food? Food { get; set; }
    }

}
