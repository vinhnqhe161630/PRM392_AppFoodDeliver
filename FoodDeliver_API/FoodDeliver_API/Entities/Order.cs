using System.ComponentModel.DataAnnotations.Schema;

namespace FoodDeliver_API.Entities
{
    [Table("Orders")] 
	public class Order
    {
        public Guid Id { get; set; }
        public Guid UserId { get; set; }
        public Guid ShopId { get; set; }
        public string CustomerName { get; set; }
        public string CustomerPhone { get; set; }
        public string CustomerAddress { get; set; }
        public DateTime OrderDate { get; set; }
        public decimal TotalAmount { get; set; }
        public string Status { get; set; }
        [ForeignKey("UserId")] public virtual Account Account { get; set; }
        [ForeignKey("ShopId")] public virtual Account Shop { get; set; }

        // Navigation property
        public ICollection<OrderDetail>? OrderDetails { get; set; }
    }

}
