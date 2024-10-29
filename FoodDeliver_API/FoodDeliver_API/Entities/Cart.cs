using System.ComponentModel.DataAnnotations.Schema;

namespace FoodDeliver_API.Entities
{
    public class Cart
    {
        public Guid Id { get; set; }
        public Guid UserId { get; set; }  
        public Guid ShopId { get; set; }
        public Guid FoodId { get; set; }
        public int Quantity { get; set; }

        [ForeignKey("UserId")] public virtual Account Account { get; set; }
        [ForeignKey("ShopId")] public virtual Account Shop { get; set; }
        [ForeignKey("FoodId")] public virtual Food Food { get; set; }

      
    }
}
