using FoodDeliver_API.Entities;

using FoodDeliver_API.ViewModel.Comments;
using FoodDeliver_API.ViewModel.Comments.FoodDeliver_API.Models;
using FoodDeliver_API.ViewModel.Comments.FoodDeliver_API.Models.DTOs;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;


    namespace FoodDeliver_API.Controllers
    {
        [Route("api/[controller]")]
        [ApiController]
        public class CommentsController : ControllerBase
        {
            private readonly ApplicationDbContext _context;

            public CommentsController(ApplicationDbContext context)
            {
                _context = context;
            }

            // GET: api/comments/food/{foodId}
            [HttpGet("food/{foodId}")]
            public async Task<ActionResult<IEnumerable<CommentDto>>> GetCommentsByFoodId(Guid foodId)
            {
                var comments = await _context.Comments
                    .Where(c => c.FoodID == foodId)
                    .Select(c => new CommentDto
                    {
                        Id = c.Id,
                        Content = c.Content,
                        Vote = c.Vote,
                        CommentDate = c.CommentDate,
                        userId = c.User.Id,
                        UserName = c.User.Name,
                        UserAva = c.User.Img
                    })
                    .ToListAsync();

            

                return Ok(comments); // Return the comments
            }

        // POST: api/comments/add
        [HttpPost("add")]
        public async Task<IActionResult> AddComment([FromBody] AddCommentDto commentDto)
        {
            if (commentDto == null || string.IsNullOrWhiteSpace(commentDto.Content))
            {
                return BadRequest("Invalid comment data.");
            }

            var comment = new Comment
            {
                Id = Guid.NewGuid(), 
                Content = commentDto.Content,
                Vote = commentDto.Vote,
                CommentDate = commentDto.CommentDate,
                UserID = commentDto.UserID,
                FoodID = commentDto.FoodID
            };

            _context.Comments.Add(comment);

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateException ex)
            {
                // Log the error (uncomment ex variable name and write a log.)
                return StatusCode(500, "An error occurred while saving the comment. Please try again.");
            }

            return CreatedAtAction(nameof(AddComment), new { id = comment.Id }, comment);
        }

        // PUT: api/comments/edit
        [HttpPut("edit")]
        public async Task<IActionResult> EditComment([FromBody] EditCommentDto editCommentDto)
        {
            if (editCommentDto == null || string.IsNullOrWhiteSpace(editCommentDto.Content))
            {
                return BadRequest("Invalid comment data.");
            }

            var comment = await _context.Comments.FindAsync(editCommentDto.Id);
            if (comment == null)
            {
                return NotFound("Comment not found.");
            }

            // Update the comment properties
            comment.Content = editCommentDto.Content;
            comment.Vote = editCommentDto.Vote;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                return StatusCode(500, "An error occurred while updating the comment. Please try again.");
            }

            return Ok(comment); // Return the updated comment
        }

        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteComment(Guid id)
        {
            // Find the comment by ID
            var comment = await _context.Comments.FindAsync(id);
            if (comment == null)
            {
                return NotFound("Comment not found.");
            }

            // Remove the comment
            _context.Comments.Remove(comment);

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (Exception ex)
            {
                // Log the exception (optional)
                return StatusCode(500, "An error occurred while deleting the comment. Please try again.");
            }

            return NoContent(); // Return 204 No Content on successful deletion
        }
        [HttpGet("check-purchase/{userId}/{foodId}")]
        public ActionResult<PurchaseCheckDto> CheckPurchase(Guid userId, Guid foodId)
        {
            // Directly check for purchases without async-await
            var hasPurchased =  _context.Order
                .Where(order => order.UserId == userId)
                .SelectMany(order => order.OrderDetails)
                .Any(orderDetail => orderDetail.FoodID == foodId);

            return Ok(new PurchaseCheckDto
            {
                FoodId = foodId,
                HasPurchased = hasPurchased
            });
        }

    }
}

