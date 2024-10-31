using System.ComponentModel.DataAnnotations.Schema;
using System.Text.Json.Serialization;

namespace FoodDeliver_API.Entities
{
    public class Comment
    {
        public Guid Id { get; set; }
        public string Content { get; set; }
        public int Vote { get; set; }

        // Foreign Keys
        [JsonIgnore]
        public Guid UserID { get; set; }
        [JsonIgnore]
        public Guid FoodID { get; set; }

        // Navigation properties
        [JsonIgnore]
        [ForeignKey("UserID")] public Account? User { get; set; }
        [JsonIgnore]
        [ForeignKey("FoodID")] public Food? Food { get; set; }
    }

}
