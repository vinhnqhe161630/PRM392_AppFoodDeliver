namespace FoodDeliver_API.ViewModel.Comment
{
    public class AddCommentDto
    {
        public string Content { get; set; }
        public int Vote { get; set; }
        public Guid UserID { get; set; }
        public Guid FoodID { get; set; }
    }
}
