﻿namespace FoodDeliver_API.Models
{
    public class Food
    {
        public Guid Id { get; set; }
        public string Name { get; set; }
        public decimal Price { get; set; }
        public string Description { get; set; }
        public string Img { get; set; }
        public bool Status { get; set; }

        // Foreign Key
        public Guid AccountID { get; set; }

        // Navigation properties
        public Account Account { get; set; }
        public ICollection<OrderDetail> OrderDetails { get; set; } = new List<OrderDetail>();
        public ICollection<Comment> Comments { get; set; } = new List<Comment>();
    }
}
