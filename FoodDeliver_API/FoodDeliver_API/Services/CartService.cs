using FoodDeliver_API.Entities;
using Microsoft.EntityFrameworkCore;

namespace FoodDeliver_API.Services
{
    public class CartService
    {
        private readonly ApplicationDbContext _context;
        public CartService(ApplicationDbContext context)
        {
            _context = context;
        }

     public async Task<List<Cart>> getCartByUserId(Guid userId)
        {
            var user = await _context.Accounts.FindAsync(userId);
            if(user == null)
            {
                throw new Exception("User not found");
            }
            return await _context.Carts.Include(o => o.Shop)
                .Include(o => o.Account)
                .Include(o=>o.Food)
                .Where(x => x.UserId == userId).ToListAsync();
        }   

        public async Task createCart(Cart order)
        {
            _context.Carts.Add(order);
            await _context.SaveChangesAsync();
          
        }  
      

    }
}
