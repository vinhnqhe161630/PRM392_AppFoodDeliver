using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore; // Ensure you have this using directive

using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using FoodDeliver_API.Entities;
using FoodDeliver_API.ViewModel.Shop;
using FoodDeliver_API.ViewModel.Food.FoodDeliver_API.Dtos;
using FoodDeliver_API.ViewModel.Comments.FoodDeliver_API.Models;

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

        [HttpGet]
        public async Task<IActionResult> GetFood()
        {

            var foods = await _context.Foods.Include(x => x.Account).Include(x => x.Comments)
              .ToListAsync();
            List<FoodDto> foodDtos = new List<FoodDto>();
            foreach(var food in foods)
            {
                // Map to FoodDto
                var foodDto = new FoodDto
                {
                    Id = food.Id,
                    Name = food.Name,
                    Price = food.Price,
                    Description = food.Description,
                    Img = food.Img,
                    Status = food.Status,
                    Account = food.Account != null ? new ShopFoodDto
                    {
                        Id = food.Account.Id,
                        Name = food.Account.Name,
                        Img = food.Account.Img,
                        Address = food.Account.Address
                    } : null,
                    Comments = food.Comments.Select(c => new CommentDto
                    {
                        Id = c.Id,
                        Content = c.Content,
                        Vote = c.Vote,

                    }).ToList()
                };
                foodDtos.Add(foodDto);
            }

            return Ok(foodDtos);
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
        public async Task<ActionResult<FoodDto>> GetFoodDetail(Guid foodId)
        {
            // Retrieve the food item with account and comments
            var food = await _context.Foods
                .Include(f => f.Account)
                .Include(f => f.Comments)
                .FirstOrDefaultAsync(f => f.Id == foodId);

            if (food == null)
            {
                return NotFound("Food not found.");
            }

            // Map to FoodDto
            var foodDto = new FoodDto
            {
                Id = food.Id,
                Name = food.Name,
                Price = food.Price,
                Description = food.Description,
                Img = food.Img,
                Status = food.Status,
                Account = food.Account != null ? new ShopFoodDto
                {
                    Id = food.Account.Id,
                    Name = food.Account.Name,
                    Img = food.Account.Img,
                    Address = food.Account.Address
                } : null,
                Comments = food.Comments.Select(c => new CommentDto
                {
                    Id = c.Id,
                    Content = c.Content,
                    Vote = c.Vote,
                
                }).ToList()
            };

            return Ok(foodDto);
        }


        // GET: api/account/{id}
        [HttpGet("{id}")]
        public async Task<ActionResult<ShopDTO>> GetAccountById(Guid id)
        {
            try
            {
                var account = await _context.Accounts
                    .Include(a => a.Foods).ThenInclude(f => f.Comments)
                    .FirstOrDefaultAsync(a => a.Id == id);

                if (account == null)
                {
                    return NotFound();
                }

                // Map to ShopDTO
                var shopDto = new ShopDTO
                {
                    Id = account.Id,
                    Name = account.Name,
                    Img = account.Img,
                    Email = account.Email,
                    Phone =  account.Phone,
                    Address = account.Address,
                    Foods = account.Foods.Select(f => new FoodDto
                    {
                        Id = f.Id,
                        Name = f.Name,
                        Price = f.Price,
                        Description = f.Description,
                        Img = f.Img,
                        Status = f.Status,
                        Comments = f.Comments.Select(c => new CommentDto
                        {
                            Id = c.Id,
                            Content = c.Content,
                            Vote = c.Vote,
                            CommentDate = c.CommentDate,
                           
                        }).ToList()
                    }).ToList()
                };

                return Ok(shopDto);
            }
            catch (Exception ex)
            {
                // Log the exception (using a logger service)
                // _logger.LogError(ex, "Error retrieving account by id: {Id}", id);
                return StatusCode(500, "Internal server error");
            }
        }

        [HttpGet("FoodQuantity/{foodId}")]
        public async Task<IActionResult> GetFoodQuantity(Guid foodId)
        {
            var totalQuantity = await _context.OrderDetails
                .Where(od => od.FoodID == foodId)
                .SumAsync(od => od.Quantity);

            return Ok(totalQuantity);
        }

    }



}
