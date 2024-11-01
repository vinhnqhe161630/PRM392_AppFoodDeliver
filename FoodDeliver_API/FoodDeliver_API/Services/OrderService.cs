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
            return await _context.Order.Include(o => o.Shop)
                .Include(o => o.Account)
                .Where(x => x.UserId == userId).ToListAsync();
        }   

        public async Task createOrder(Order order)
        {
            _context.Order.Add(order);
            await _context.SaveChangesAsync();
          
        }
        public async Task<Order> getOrder()
        {
            Order o=_context.Order.OrderByDescending(c=>c.OrderDate).First();
            return o;

        }
        public async Task createOrderDetail(OrderDetail orderDetail)
        {
            _context.OrderDetails.Add(orderDetail);
            await _context.SaveChangesAsync();
          
        }
        public async Task<List<OrderDetail>> getOrderDetail(Guid id)
        {
            var order  = await _context.Order.Where(x => x.Id == id).FirstOrDefaultAsync();
            if(order == null)
            {
              throw new Exception("Order not found");
            }
            var orderDetails =   await _context.OrderDetails.Include(o => o.Food).Where(x => x.OrderID == id).ToListAsync();
           
            return orderDetails;
        }
        public async Task<Account> getUser(Guid id)
        {
            Account account=await _context.Accounts.FirstOrDefaultAsync(x=>x.Id==id);
            return account;

        }
        public async Task<List<Cart>> getCartByUserId(Guid userId, Guid shopId)
        {
            var user = await _context.Accounts.FindAsync(userId);
            if (user == null)
            {
                throw new Exception("User not found");
            }
            return await _context.Carts.Include(o => o.Shop)
                .Include(o => o.Account)
                .Include(o => o.Food)
                .Where(x => x.UserId == userId&&x.ShopId==shopId)
                .ToListAsync();
        }
        public async Task<List<Account>> GetShopsFromCarts(Guid userId)
        {
            var user = await _context.Accounts.FindAsync(userId);
            if (user == null)
            {
                throw new Exception("User not found");
            }

            var accounts = await _context.Carts
                .Include(o => o.Account)
                .Where(x => x.UserId == userId)
                .Select(x => x.Shop)
                .Distinct()
                .ToListAsync();

            return accounts;
        }
        public async Task RemoveFromCarts(Guid userId)
        {
            var user = await _context.Accounts.FindAsync(userId);
            if (user == null)
            {
                throw new Exception("User not found");
            }

            var accounts = await _context.Carts
                .Include(o => o.Account)
                .Where(x => x.UserId == userId)
                .ToListAsync();
            _context.Carts.RemoveRange(accounts);
            _context.SaveChanges();
        }

    }
}
