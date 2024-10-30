USE [PRM392]
GO

INSERT [dbo].[Accounts] ([Id], [Name], [Email], [Pass], [Img], [Phone], [Address], [Role], [Status]) VALUES (N'a0000002-0000-0000-0000-000000000002', N'Bob Tran', N'bob.tran@example.com', N'$2a$11$COQORlVntIAIXXyZxibQ5OnSVYClO6rgeFGhVLnzPzvX06tZd7AOO', N'bob.png', N'0987654321', N'456 Park Ave, Ho Chi Minh City', N'User', 1)
GO
INSERT [dbo].[Accounts] ([Id], [Name], [Email], [Pass], [Img], [Phone], [Address], [Role], [Status]) VALUES (N'a0000003-0000-0000-0000-000000000003', N'Charlie Le', N'charlie.le@example.com', N'$2a$11$COQORlVntIAIXXyZxibQ5OnSVYClO6rgeFGhVLnzPzvX06tZd7AOO', N'charlie.png', N'0112233445', N'789 Market St, Da Nang', N'Admin', 1)
GO
INSERT [dbo].[Accounts] ([Id], [Name], [Email], [Pass], [Img], [Phone], [Address], [Role], [Status]) VALUES (N'a0000004-0000-0000-0000-000000000004', N'David Kim', N'david.kim@example.com', N'$2a$11$COQORlVntIAIXXyZxibQ5OnSVYClO6rgeFGhVLnzPzvX06tZd7AOO', N'david.png', N'0123456780', N'321 Central Blvd, Hanoi', N'Shop', 1)
GO
INSERT [dbo].[Accounts] ([Id], [Name], [Email], [Pass], [Img], [Phone], [Address], [Role], [Status]) VALUES (N'a0000005-0000-0000-0000-000000000005', N'Eva Pham', N'eva.pham@example.com', N'$2a$11$COQORlVntIAIXXyZxibQ5OnSVYClO6rgeFGhVLnzPzvX06tZd7AOO', N'eva.png', N'0987654320', N'654 Ocean Drive, Ho Chi Minh City', N'Shop', 1)
GO
INSERT [dbo].[Accounts] ([Id], [Name], [Email], [Pass], [Img], [Phone], [Address], [Role], [Status]) VALUES (N'a0000006-0000-0000-0000-000000000006', N'Frank Nguyen', N'frank.nguyen@example.com', N'$2a$11$COQORlVntIAIXXyZxibQ5OnSVYClO6rgeFGhVLnzPzvX06tZd7AOO', N'frank.png', N'0112233446', N'987 Lake St, Da Nang', N'Shop', 1)
GO
INSERT [dbo].[Accounts] ([Id], [Name], [Email], [Pass], [Img], [Phone], [Address], [Role], [Status]) VALUES (N'a0000001-0000-0000-0000-000000010001', N'Alice Nguyen', N'123@gmail.com', N'$2a$11$COQORlVntIAIXXyZxibQ5OnSVYClO6rgeFGhVLnzPzvX06tZd7AOO', N'alice.png', N'0123456789', N'123 Main St, Hanoi', N'Shop', 1)
GO
INSERT [dbo].[Accounts] ([Id], [Name], [Email], [Pass], [Img], [Phone], [Address], [Role], [Status]) VALUES (N'60396245-056f-40be-3d0d-08dcf8f46b6c', N'khiem', N'khiemvu08@gmail.com', N'$2a$11$6hQbLtRQH.vtkKP0hEw5ze3rwVT0nwwXGHY7H7FX0oCCeBMtDAbX2',  N'alice.png', N'0123456789', N'123 Main St, Hanoi', N'User', 1)
GO

		   -- Mk : string
INSERT INTO [dbo].[Accounts]
           ([Id]
           ,[Name]
           ,[Email]
           ,[Pass]
           ,[Img]
           ,[Phone]
           ,[Address]
           ,[Role]
           ,[Status])
     VALUES
           (CAST('A0000004-0000-0000-0000-000000000004' AS uniqueidentifier), 'David Kim', 'david.kim@example.com', '$2a$11$COQORlVntIAIXXyZxibQ5OnSVYClO6rgeFGhVLnzPzvX06tZd7AOO', 'david.png', '0123456780', '321 Central Blvd, Hanoi', 'Shop', 1),
           (CAST('A0000005-0000-0000-0000-000000000005' AS uniqueidentifier), 'Eva Pham', 'eva.pham@example.com', '$2a$11$COQORlVntIAIXXyZxibQ5OnSVYClO6rgeFGhVLnzPzvX06tZd7AOO', 'eva.png', '0987654320', '654 Ocean Drive, Ho Chi Minh City', 'Shop', 1),
           (CAST('A0000006-0000-0000-0000-000000000006' AS uniqueidentifier), 'Frank Nguyen', 'frank.nguyen@example.com', '$2a$11$COQORlVntIAIXXyZxibQ5OnSVYClO6rgeFGhVLnzPzvX06tZd7AOO', 'frank.png', '0112233446', '987 Lake St, Da Nang', 'Shop', 1);
GO



INSERT INTO [dbo].[Foods]
           ([Id]
           ,[Name]
           ,[Price]
           ,[Description]
           ,[Img]
           ,[Status]
           ,[AccountID])
     VALUES
           (CAST('F0000001-0000-0000-0000-000000000001' AS uniqueidentifier), 'Phở Bò', 50.00, 'Món phở truyền thống với thịt bò và gia vị', 'pho_bo.png', 1, CAST('A0000004-0000-0000-0000-000000000004' AS uniqueidentifier)),
           (CAST('F0000002-0000-0000-0000-000000000002' AS uniqueidentifier), 'Bánh Mì', 20.00, 'Bánh mì kẹp thịt và rau sống', 'banh_mi.png', 1, CAST('A0000005-0000-0000-0000-000000000005' AS uniqueidentifier)),
           (CAST('F0000003-0000-0000-0000-000000000003' AS uniqueidentifier), 'Gỏi Cuốn', 25.00, 'Gỏi cuốn tươi mát với tôm và rau sống', 'goi_cuon.png', 1, CAST('A0000006-0000-0000-0000-000000000006' AS uniqueidentifier)),
           (CAST('F0000004-0000-0000-0000-000000000004' AS uniqueidentifier), 'Cơm Tấm', 30.00, 'Cơm tấm sườn nướng và trứng ốp la', 'com_tam.png', 1, CAST('A0000004-0000-0000-0000-000000000004' AS uniqueidentifier)),
           (CAST('F0000005-0000-0000-0000-000000000005' AS uniqueidentifier), 'Mì Quảng', 45.00, 'Món mì quảng đặc sản miền Trung', 'mi_quang.png', 1, CAST('A0000005-0000-0000-0000-000000000005' AS uniqueidentifier));
GO


INSERT INTO [dbo].[Comments]
           ([Id]
           ,[Content]
           ,[Vote]
           ,[UserID]
           ,[FoodID])
     VALUES
           (CAST('C0000001-0000-0000-0000-000000000001' AS uniqueidentifier), N'Phở bò rất ngon, tôi rất thích!', 5, CAST('A0000001-0000-0000-0000-000000000001' AS uniqueidentifier), CAST('F0000001-0000-0000-0000-000000000001' AS uniqueidentifier)),
           (CAST('C0000002-0000-0000-0000-000000000002' AS uniqueidentifier), N'Bánh mì khá ổn, nhưng hơi ngấy.', 4, CAST('A0000002-0000-0000-0000-000000000002' AS uniqueidentifier), CAST('F0000002-0000-0000-0000-000000000002' AS uniqueidentifier)),
           (CAST('C0000003-0000-0000-0000-000000000003' AS uniqueidentifier), N'Gỏi cuốn tươi ngon, rất thích hợp cho mùa hè.', 5, CAST('A0000001-0000-0000-0000-000000000001' AS uniqueidentifier), CAST('F0000003-0000-0000-0000-000000000003' AS uniqueidentifier)),
           (CAST('C0000004-0000-0000-0000-000000000004' AS uniqueidentifier), N'Cơm tấm sườn nướng quá tuyệt vời!', 5, CAST('A0000003-0000-0000-0000-000000000003' AS uniqueidentifier), CAST('F0000004-0000-0000-0000-000000000004' AS uniqueidentifier)),
           (CAST('C0000005-0000-0000-0000-000000000005' AS uniqueidentifier), N'Mì Quảng rất ngon, sẽ quay lại!', 5, CAST('A0000002-0000-0000-0000-000000000002' AS uniqueidentifier), CAST('F0000005-0000-0000-0000-000000000005' AS uniqueidentifier));
GO

INSERT INTO [dbo].[Carts]
           ([Id]
           ,[UserId]
           ,[ShopId]
           ,[FoodId]
           ,[Quantity])
     VALUES
           (NEWID()  -- Generates a new unique identifier
           ,'60396245-056F-40BE-3D0D-08DCF8F46B6C'  -- UserId
           ,'A0000001-0000-0000-0000-000000010001'  -- ShopId
           ,'F0000004-0000-0000-0000-000000000004'  -- FoodId
           ,10)  -- Quantity

INSERT INTO [dbo].[Orders]
           ([Id]
           ,[UserId]
           ,[ShopId]
           ,[CustomerName]
           ,[CustomerPhone]
           ,[CustomerAddress]
           ,[OrderDate]
           ,[TotalAmount]
           ,[Status]
           ,[AccountId])
VALUES
           ('C0000001-0000-0000-0000-000000000001', -- Generates a new uniqueidentifier for Id
           'A0000001-0000-0000-0000-000000000001', -- Generates a new uniqueidentifier for UserId
           'A0000004-0000-0000-0000-000000000004', -- Generates a new uniqueidentifier for ShopId
            'John Doe', -- CustomerName
            '123-456-7890', -- CustomerPhone
            '123 Main St, Cityville, ST, 12345', -- CustomerAddress
            GETDATE(), -- Current date and time for OrderDate
            99.99, -- TotalAmount
            'Pending', -- Status
           'A0000001-0000-0000-0000-000000000001'), -- Generates a new uniqueidentifier for AccountId

           ('C0000001-0000-0000-0000-000000000002',
            'A0000001-0000-0000-0000-000000000001',
           'A0000004-0000-0000-0000-000000000004',
            'Jane Smith',
            '098-765-4321',
            '456 Elm St, Townsville, ST, 54321',
            GETDATE(),
            149.50,
            'Completed',
            'A0000001-0000-0000-0000-000000000001'),

           ('C0000001-0000-0000-0000-000000000003',
           'A0000001-0000-0000-0000-000000000001',
           'A0000004-0000-0000-0000-000000000004',
            'Alice Johnson',
            '555-123-4567',
            '789 Oak St, Villageville, ST, 67890',
            GETDATE(),
            75.00,
            'Shipped',
           'A0000001-0000-0000-0000-000000000001');
GO

select * from Accounts
INSERT INTO [dbo].[OrderDetails]
           ([Id]
           ,[OrderID]
           ,[FoodID]
           ,[Quantity]
           ,[Price]
           ,[Total])
VALUES
           (NEWID(), -- Generates a new uniqueidentifier for Id
          'C0000001-0000-0000-0000-000000000001', -- Replace with an existing OrderID
            'F0000001-0000-0000-0000-000000000001', -- Replace with an existing FoodID
            2, -- Quantity
            15.99, -- Price per item
            31.98) -- Total (Quantity * Price)

           ,(NEWID(), -- New Id
          'C0000001-0000-0000-0000-000000000002', -- Replace with another existing OrderID
           'F0000001-0000-0000-0000-000000000001', -- Replace with another existing FoodID
            1, -- Quantity
            20.00, -- Price per item
            20.00) -- Total

           ,(NEWID(), -- New Id
            'C0000001-0000-0000-0000-000000000003', -- Replace with another existing OrderID
            'F0000001-0000-0000-0000-000000000001', -- Replace with another existing FoodID
            3, -- Quantity
            9.99, -- Price per item
            29.97); -- Total
GO
