using System.ComponentModel.DataAnnotations.Schema;

namespace FoodDeliver_API.Models
{
    public class OrderDetail
    {
        public Guid Id { get; set; }

        // Foreign Keys
        public Guid OrderID { get; set; }
        public Guid FoodID { get; set; }

        public int Quantity { get; set; }
        public decimal Price { get; set; }
        public decimal Total { get; set; }

        // Navigation properties
        [ForeignKey("OrderID")]  public virtual Order Order { get; set; }
        [ForeignKey("FoodID")] public virtual Food Food { get; set; }
    }

}
