USE [ISE1723_Stupid]
GO
/****** Object:  Table [dbo].[Department]    Script Date: 2/21/2023 3:05:40 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Department](
	[did] [int] NOT NULL,
	[dname] [varchar](150) NOT NULL,
 CONSTRAINT [PK_Department] PRIMARY KEY CLUSTERED 
(
	[did] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Student]    Script Date: 2/21/2023 3:05:40 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Student](
	[sid] [int] IDENTITY(1,1) NOT NULL,
	[sname] [varchar](150) NOT NULL,
	[gender] [bit] NOT NULL,
	[dob] [date] NULL,
	[did] [int] NULL,
 CONSTRAINT [PK_Student] PRIMARY KEY CLUSTERED 
(
	[sid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[User]    Script Date: 2/21/2023 3:05:40 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[User](
	[username] [varchar](150) NOT NULL,
	[password] [varchar](150) NOT NULL,
	[displayname] [varchar](150) NOT NULL,
 CONSTRAINT [PK_User] PRIMARY KEY CLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Department] ([did], [dname]) VALUES (1, N'Informtion Technology')
INSERT [dbo].[Department] ([did], [dname]) VALUES (2, N'Business Administration')
INSERT [dbo].[Department] ([did], [dname]) VALUES (3, N'Graphical Design')
GO
SET IDENTITY_INSERT [dbo].[Student] ON 

INSERT [dbo].[Student] ([sid], [sname], [gender], [dob], [did]) VALUES (2, N'Hoang Mai Phuong', 0, CAST(N'2003-01-29' AS Date), 1)
INSERT [dbo].[Student] ([sid], [sname], [gender], [dob], [did]) VALUES (3, N'Nguyen Thi Cam Tu', 0, CAST(N'2003-01-02' AS Date), 2)
INSERT [dbo].[Student] ([sid], [sname], [gender], [dob], [did]) VALUES (4, N'Ngo Tung Son', 1, CAST(N'1997-11-13' AS Date), 2)
SET IDENTITY_INSERT [dbo].[Student] OFF
GO
INSERT [dbo].[User] ([username], [password], [displayname]) VALUES (N'huyen', N'huyen', N'Ms Huyen')
INSERT [dbo].[User] ([username], [password], [displayname]) VALUES (N'mra', N'mra', N'Mr A')
INSERT [dbo].[User] ([username], [password], [displayname]) VALUES (N'phuong', N'phuong', N'Ms Phuong')
INSERT [dbo].[User] ([username], [password], [displayname]) VALUES (N'sonnt', N'sonnt', N'Mr Superman')
GO
ALTER TABLE [dbo].[Student]  WITH CHECK ADD  CONSTRAINT [FK_Student_Department] FOREIGN KEY([did])
REFERENCES [dbo].[Department] ([did])
GO
ALTER TABLE [dbo].[Student] CHECK CONSTRAINT [FK_Student_Department]
GO
