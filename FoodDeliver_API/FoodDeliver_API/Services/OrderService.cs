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



    }
}
