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
        public Account User { get; set; }
        public Food Food { get; set; }
    }

}
