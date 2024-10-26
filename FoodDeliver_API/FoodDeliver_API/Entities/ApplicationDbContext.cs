﻿using Microsoft.EntityFrameworkCore;

namespace FoodDeliver_API.Models
{
    public class ApplicationDbContext : DbContext
    {
        public DbSet<Account> Accounts { get; set; }
        public DbSet<Food> Foods { get; set; }
        public DbSet<Order> Orders { get; set; }
        public DbSet<OrderDetail> OrderDetails { get; set; }
        public DbSet<Comment> Comments { get; set; }

        // Constructor for injecting options
        public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options) : base(options)
        {
        }

        // Configure entity relationships
        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            base.OnModelCreating(modelBuilder);

            // Account to Food (One-to-Many)
            modelBuilder.Entity<Account>()
                .HasMany(a => a.Foods)
                .WithOne(f => f.Account)
                .HasForeignKey(f => f.AccountID)
                .OnDelete(DeleteBehavior.Cascade); // Allow cascading deletes if needed

            // Food to OrderDetail (One-to-Many)
            modelBuilder.Entity<Food>()
                .HasMany(f => f.OrderDetails)
                .WithOne(od => od.Food)
                .HasForeignKey(od => od.FoodID)
                .OnDelete(DeleteBehavior.Cascade); // Allow cascading deletes if needed

            // Food to Comment (One-to-Many)
            modelBuilder.Entity<Food>()
                .HasMany(f => f.Comments)
                .WithOne(c => c.Food)
                .HasForeignKey(c => c.FoodID)
                .OnDelete(DeleteBehavior.Restrict); // Avoid cascade delete for comments

            // Order to OrderDetail (One-to-Many)
            modelBuilder.Entity<Order>()
                .HasMany(o => o.OrderDetails)
                .WithOne(od => od.Order)
                .HasForeignKey(od => od.OrderID)
                .OnDelete(DeleteBehavior.Cascade); // Allow cascading deletes if needed

            // Account to Comment (One-to-Many)
            modelBuilder.Entity<Comment>()
                .HasOne(c => c.User)
                .WithMany(a => a.Comments) // Make sure Comments is defined in Account
                .HasForeignKey(c => c.UserID)
                .OnDelete(DeleteBehavior.Restrict); // Avoid cascade delete for comments

            // Fluent API to specify precision for decimal fields
            modelBuilder.Entity<Food>()
                .Property(f => f.Price)
                .HasColumnType("decimal(18,2)");

            modelBuilder.Entity<OrderDetail>()
                .Property(od => od.Price)
                .HasColumnType("decimal(18,2)");

            modelBuilder.Entity<OrderDetail>()
                .Property(od => od.Total)
                .HasColumnType("decimal(18,2)");

            modelBuilder.Entity<Order>()
                .Property(o => o.TotalAmount)
                .HasColumnType("decimal(18,2)");

            // Optionally, you can add further configurations here for any constraints or indexes.
        }
    }
}