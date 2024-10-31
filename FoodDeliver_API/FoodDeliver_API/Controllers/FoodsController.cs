using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore; // Ensure you have this using directive

using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using FoodDeliver_API.Entities;

namespace FoodDeliver_API.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class FoodsController : ControllerBase
    {
        private readonly ApplicationDbContext _context;

        public FoodsController(ApplicationDbContext context)
        {
            _context = context;
        }

        // GET: api/foods/account/{accountId}
        [HttpGet("account/{accountId}")]
        public async Task<ActionResult<IEnumerable<Food>>> GetFoodsByAccountId(Guid accountId)
        {
            // Retrieve foods by AccountID
            var foods = await _context.Foods
                .Where(f => f.AccountID == accountId)
                 .Include(f => f.Comments)
                .ToListAsync();

            if (foods == null || !foods.Any())
            {
                return NotFound("No foods found for this account.");
            }

            return Ok(foods); // Return the list of foods with status code 200
        }



        [HttpGet("{foodId}/details")]
        public async Task<ActionResult<Food>> GetFoodDetail(Guid foodId)
        {
            // Retrieve the food item with comments
            var food = await _context.Foods
                
                .Include(f => f.Account)
                .Include(f => f.Comments)
                .FirstOrDefaultAsync(f => f.Id == foodId);

            if (food == null)
            {
                return NotFound("Food not found.");
            }

            return Ok(food); // Return the food item with comments included
        }
    }


}
