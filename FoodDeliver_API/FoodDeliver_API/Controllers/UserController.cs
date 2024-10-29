using AutoMapper;
using FoodDeliver_API.Entities;
using FoodDeliver_API.Entities;
using FoodDeliver_API.Services;
using FoodDeliver_API.ViewModel.Shop;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace FoodDeliver_API.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class UserController : ControllerBase
    {
        private readonly IMapper _mapper;
        private readonly ApplicationDbContext _context;

        public UserController(IMapper mapper, ApplicationDbContext context)
        {
            _mapper = mapper;
            _context = context;
        }   

        [HttpGet("Shop")]
        public async Task<IActionResult> GetShop()
        {
            try
            {
                List<Account> shops = await _context.Accounts.Where(u => u.Role == "Shop")
               .Include(s => s.Orders).Include(s => s.Foods).ThenInclude(s => s.Comments).ToListAsync();
                var shopVms = _mapper.Map<List<ShopViewModel>>(shops);
                int i = 0;
                foreach (Account shop in shops)
                {
                    shopVms[i].TotalOrder = shop.Orders.Count;

                    foreach (Food food in shop.Foods)
                    {
                        var vote = food.Comments.Average(s => s.Vote);
                        shopVms[i].Vote = vote;
                    }
                    i++;
                }

                return Ok(shopVms);
            }
            catch(Exception e)
            {
                return BadRequest(new { message = e.Message });
            }   
           
        }
        [HttpGet("Shop/{id}")]
        public async Task<IActionResult> GetAction(Guid id)
        {
            Account shop = await _context.Accounts.Where(u => u.Id == id)
                .Include(s => s.Orders).Include(s => s.Foods).ThenInclude(s => s.Comments).FirstOrDefaultAsync();
            var shopVm = _mapper.Map<ShopViewModel>(shop);
            shopVm.TotalOrder = shop.Orders.Count;
            foreach (Food food in shop.Foods)
            {
                var vote = food.Comments.Average(s => s.Vote);
                shopVm.Vote = vote;
            }

            return Ok("Shop");
        }

    }
}
