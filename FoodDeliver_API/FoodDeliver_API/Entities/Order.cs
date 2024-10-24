﻿namespace FoodDeliver_API.Models
{
    public class Order
    {
        public Guid Id { get; set; }
        public string CustomerName { get; set; }
        public string CustomerPhone { get; set; }
        public string CustomerAddress { get; set; }
        public DateTime OrderDate { get; set; }
        public decimal TotalAmount { get; set; }
        public string Status { get; set; }

        // Navigation property
        public ICollection<OrderDetail> OrderDetails { get; set; }
    }

}
