USE [FruitShop]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 11/10/2020 9:29:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account](
	[accountid] [varchar](50) NOT NULL,
	[password] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Account] PRIMARY KEY CLUSTERED 
(
	[accountid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Functionality]    Script Date: 11/10/2020 9:29:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Functionality](
	[functionid] [int] NOT NULL,
	[url] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Functionality] PRIMARY KEY CLUSTERED 
(
	[functionid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Group]    Script Date: 11/10/2020 9:29:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Group](
	[groupid] [int] NOT NULL,
	[groupname] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Group] PRIMARY KEY CLUSTERED 
(
	[groupid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Group_Account]    Script Date: 11/10/2020 9:29:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Group_Account](
	[accountid] [varchar](50) NOT NULL,
	[groupid] [int] NOT NULL,
 CONSTRAINT [PK_Group_Account] PRIMARY KEY CLUSTERED 
(
	[accountid] ASC,
	[groupid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Group_Functionality]    Script Date: 11/10/2020 9:29:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Group_Functionality](
	[groupid] [int] NOT NULL,
	[functionid] [int] NOT NULL,
 CONSTRAINT [PK_Group_Functionality] PRIMARY KEY CLUSTERED 
(
	[groupid] ASC,
	[functionid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Group_Product]    Script Date: 11/10/2020 9:29:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Group_Product](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[group] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Group_Product] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Product]    Script Date: 11/10/2020 9:29:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product](
	[itemID] [int] IDENTITY(1,1) NOT NULL,
	[itemName] [varchar](50) NOT NULL,
	[description] [varchar](max) NOT NULL,
	[image] [varchar](50) NOT NULL,
	[itemRate] [int] NOT NULL,
	[price] [real] NOT NULL,
	[itemStatus] [bit] NOT NULL,
	[menuID] [int] NOT NULL,
 CONSTRAINT [PK_Product] PRIMARY KEY CLUSTERED 
(
	[itemID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
INSERT [dbo].[Account] ([accountid], [password]) VALUES (N'admin', N'123')
INSERT [dbo].[Account] ([accountid], [password]) VALUES (N'guess', N'123')
INSERT [dbo].[Account] ([accountid], [password]) VALUES (N'member', N'123')
GO
INSERT [dbo].[Functionality] ([functionid], [url]) VALUES (1, N'/updateproduct')
INSERT [dbo].[Functionality] ([functionid], [url]) VALUES (2, N'/delete')
INSERT [dbo].[Functionality] ([functionid], [url]) VALUES (3, N'/addproduct')
INSERT [dbo].[Functionality] ([functionid], [url]) VALUES (4, N'/List')
INSERT [dbo].[Functionality] ([functionid], [url]) VALUES (5, N'/product-single')
GO
INSERT [dbo].[Group] ([groupid], [groupname]) VALUES (1, N'admin')
INSERT [dbo].[Group] ([groupid], [groupname]) VALUES (2, N'mod')
INSERT [dbo].[Group] ([groupid], [groupname]) VALUES (3, N'member')
GO
INSERT [dbo].[Group_Account] ([accountid], [groupid]) VALUES (N'admin', 1)
INSERT [dbo].[Group_Account] ([accountid], [groupid]) VALUES (N'guess', 3)
INSERT [dbo].[Group_Account] ([accountid], [groupid]) VALUES (N'member', 2)
GO
INSERT [dbo].[Group_Functionality] ([groupid], [functionid]) VALUES (1, 1)
INSERT [dbo].[Group_Functionality] ([groupid], [functionid]) VALUES (1, 2)
INSERT [dbo].[Group_Functionality] ([groupid], [functionid]) VALUES (1, 3)
INSERT [dbo].[Group_Functionality] ([groupid], [functionid]) VALUES (1, 4)
INSERT [dbo].[Group_Functionality] ([groupid], [functionid]) VALUES (1, 5)
INSERT [dbo].[Group_Functionality] ([groupid], [functionid]) VALUES (2, 1)
INSERT [dbo].[Group_Functionality] ([groupid], [functionid]) VALUES (2, 4)
INSERT [dbo].[Group_Functionality] ([groupid], [functionid]) VALUES (2, 5)
INSERT [dbo].[Group_Functionality] ([groupid], [functionid]) VALUES (3, 4)
INSERT [dbo].[Group_Functionality] ([groupid], [functionid]) VALUES (3, 5)
GO
SET IDENTITY_INSERT [dbo].[Group_Product] ON 

INSERT [dbo].[Group_Product] ([id], [group]) VALUES (1, N'Vegettable')
INSERT [dbo].[Group_Product] ([id], [group]) VALUES (2, N'Fruits')
INSERT [dbo].[Group_Product] ([id], [group]) VALUES (3, N'Juice')
INSERT [dbo].[Group_Product] ([id], [group]) VALUES (4, N'Dried')
SET IDENTITY_INSERT [dbo].[Group_Product] OFF
GO
SET IDENTITY_INSERT [dbo].[Product] ON 

INSERT [dbo].[Product] ([itemID], [itemName], [description], [image], [itemRate], [price], [itemStatus], [menuID]) VALUES (1, N'Bell Pepper', N'A small river named Duden flows by their place and supplies it with the necessary regelialia. It is a paradisematic country, in which roasted parts of sentences fly into your mouth. Text should turn around and return to its own, safe country. But nothing the copy said could convince her and so it didnÃ¢??t take long until.', N'images/product-1.jpg', 0, 120, 1, 1)
INSERT [dbo].[Product] ([itemID], [itemName], [description], [image], [itemRate], [price], [itemStatus], [menuID]) VALUES (3, N'Green Beans', N'Good', N'images/product-3.jpg', 5, 120, 1, 2)
INSERT [dbo].[Product] ([itemID], [itemName], [description], [image], [itemRate], [price], [itemStatus], [menuID]) VALUES (4, N'Purple Cabbage', N'Good', N'images/product-4.jpg', 5, 120, 1, 1)
INSERT [dbo].[Product] ([itemID], [itemName], [description], [image], [itemRate], [price], [itemStatus], [menuID]) VALUES (5, N'Tomatoe', N'Good', N'images/product-5.jpg', 5, 120, 1, 2)
INSERT [dbo].[Product] ([itemID], [itemName], [description], [image], [itemRate], [price], [itemStatus], [menuID]) VALUES (6, N'Brocolli', N'Normal', N'images/product-6.jpg', 5, 120, 1, 1)
INSERT [dbo].[Product] ([itemID], [itemName], [description], [image], [itemRate], [price], [itemStatus], [menuID]) VALUES (7, N'Carrots', N'  Good for you', N'images/product-7.jpg', 0, 200, 1, 1)
INSERT [dbo].[Product] ([itemID], [itemName], [description], [image], [itemRate], [price], [itemStatus], [menuID]) VALUES (8, N'Fruit Juice', N'A small river named Duden flows by their place and supplies it with the necessary regelialia. It is a paradisematic country, in which roasted parts of sentences fly into your mouth. Text should turn around and return to its own, safe country. But nothing the copy said could convince her and so it didnâ??t take long until.', N'images/product-8.jpg', 0, 170, 1, 3)
INSERT [dbo].[Product] ([itemID], [itemName], [description], [image], [itemRate], [price], [itemStatus], [menuID]) VALUES (11, N'Onion', N'Onion GOOOD !!!!', N'images/product-9.jpg', 0, 200, 1, 4)
INSERT [dbo].[Product] ([itemID], [itemName], [description], [image], [itemRate], [price], [itemStatus], [menuID]) VALUES (12, N'Strawberry', N'The garden strawberry (or simply strawberry; Fragaria Ã? ananassa)[1] is a widely grown hybrid species of the genus Fragaria, collectively known as the strawberries, which are cultivated worldwide for their fruit. The fruit is widely appreciated for its characteristic aroma, bright red color, juicy texture, and sweetness. It is consumed in large quantities, either fresh or in such prepared foods as jam, juice, pies, ice cream, milkshakes, and chocolates. Artificial strawberry flavorings and aromas are also widely used in products such as candy, soap, lip gloss, perfume, and many others.', N'images/product-2.jpg', 0, 190, 1, 2)
SET IDENTITY_INSERT [dbo].[Product] OFF
GO
