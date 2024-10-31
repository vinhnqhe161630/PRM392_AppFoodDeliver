using AutoMapper;
using FoodDeliver_API.Entities;
using FoodDeliver_API.ViewModel.Food;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace FoodDeliver_API.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class FoodController : ControllerBase
    {
        private readonly IMapper _mapper;
        private ApplicationDbContext _context;
        public FoodController(ApplicationDbContext context,IMapper mapper)
        {
            _context = context;
            _mapper = mapper;
        }
        [HttpGet]
        public async Task<IActionResult> GetFood()
        {

            var foods=  await _context.Foods.Include(x => x.Account).Include(x => x.Comments)
              .ToListAsync();

            var foodvm = _mapper.Map<List<FoodViewModel>>(foods);

            return Ok(foodvm);
        }
    }
    
}
