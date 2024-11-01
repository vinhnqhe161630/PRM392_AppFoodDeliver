namespace FoodDeliver_API.ViewModel.Comments
{
    public class AddCommentDto
    {
        public string Content { get; set; }
        public int Vote { get; set; }
        public DateTime CommentDate { get; set; }
        public Guid UserID { get; set; }
        public Guid FoodID { get; set; }
    }
}
