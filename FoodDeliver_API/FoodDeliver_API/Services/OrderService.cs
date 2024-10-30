using FoodDeliver_API.Entities;
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
            var user = await _context.Accounts.FindAsync(userId);
            if(user == null)
            {
                throw new Exception("User not found");
            }
            return await _context.Orders.Include(o => o.Shop)
                .Include(o => o.Account)
                .Where(x => x.UserId == userId).ToListAsync();
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
        public async Task<List<OrderDetail>> getOrderDetail(Guid id)
        {
            var order  = await _context.Orders.Where(x => x.Id == id).FirstOrDefaultAsync();
            if(order == null)
            {
              throw new Exception("Order not found");
            }
            var orderDetails =   await _context.OrderDetails.Include(o => o.Food).Where(x => x.OrderID == id).ToListAsync();
           
            return orderDetails;
        }

    }
}
