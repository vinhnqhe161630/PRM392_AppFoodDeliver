
using FoodDeliver_API.Models;
using Microsoft.EntityFrameworkCore;
namespace FoodDeliver_API.Services
{
    public class FoodService
    {
        private readonly ApplicationDbContext _context;
        public FoodService(ApplicationDbContext context)
        {
            _context = context;
        }

        public async Task<List<Food>> getFood()
        {
            return await _context.Foods.ToListAsync();
        }
       

        public async Task<Food> getFoodById(Guid id)
        {
            return await _context.Foods.FirstOrDefaultAsync(x => x.Id == id);
        }

        public async Task<Food> createFood(Food food)
        {
            _context.Foods.Add(food);
            await _context.SaveChangesAsync();
            return food;
        }

        public async Task<Food> updateFood(Food food)
        {
            _context.Foods.Update(food);
            await _context.SaveChangesAsync();
            return food;
        }

        public async Task<Food> deleteFood(Guid id)
        {
            var food = await _context.Foods.FirstOrDefaultAsync(x => x.Id == id);
            _context.Foods.Remove(food);
            await _context.SaveChangesAsync();
            return food;
        }
    }
}
