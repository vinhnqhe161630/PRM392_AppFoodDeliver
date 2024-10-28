using FoodDeliver_API.Models;
using Microsoft.EntityFrameworkCore;

namespace FoodDeliver_API.Services
{
    public class OrderService
    {
        private readonly ApplicationDbContext _context;
        public OrderService(ApplicationDbContext context)
        {
            _context = context;
        }

     public async Task<List<Order>> getOrderByUserId(Guid userId)
        {
            return await _context.Orders.Where(x => x.UserId == userId).ToListAsync();
        }   

        public async Task createOrder(Order order)
        {
            _context.Orders.Add(order);
            await _context.SaveChangesAsync();
          
        }  
        public async Task createOrderDetail(OrderDetail orderDetail)
        {
            _context.OrderDetails.Add(orderDetail);
            await _context.SaveChangesAsync();
          
        }

    }
}
