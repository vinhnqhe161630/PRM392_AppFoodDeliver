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
        public async Task<Cart?> getCartByCartId(Guid cartId)
        {
            try
            {
                Cart a = await _context.Carts.Include(o => o.Shop)
                .Include(o => o.Account)
                .Include(o => o.Food)
                .FirstOrDefaultAsync(o => o.Id == cartId);
                return a;
            }
            catch
            {
                return null;
            }
            
        }

        public async Task createCart(Cart order)
        {
            var cart = await _context.Carts.FirstOrDefaultAsync(o=>o.FoodId == order.FoodId&&o.UserId==order.UserId);
            if (cart == null)
            {
                _context.Carts.Add(order);
                await _context.SaveChangesAsync();
            }
            else
            {
                cart.Quantity += 1;
                _context.Carts.Update(cart);
                await _context.SaveChangesAsync();
            }
          
        }
        public async Task deleteCart(Cart cart)
        {
            _context.Carts.Remove(cart);
            await _context.SaveChangesAsync();

        }
        public async Task increaseCart(Cart cart)
        {
            cart.Quantity += 1;
            _context.Carts.Remove(cart);
            await _context.SaveChangesAsync();

        }
        public async Task decreaseCart(Cart cart)
        {
           
            cart.Quantity -= 1;
            _context.Carts.Remove(cart);
            await _context.SaveChangesAsync();

        }
        public async Task<Guid> getShopByFoodId(Guid foodId)
        {
           Food food= await _context.Foods.FirstOrDefaultAsync(o=>o.Id==foodId);
            if (food == null) return Guid.Empty;
            return food.AccountID;
        }
    }
}
