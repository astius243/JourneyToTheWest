USE [master]
GO
/****** Object:  Database [TayDuKy]    Script Date: 12/1/2020 9:30:13 PM ******/
CREATE DATABASE [TayDuKy]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'TayDuKy', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.MSSQLSERVER\MSSQL\DATA\TayDuKy.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'TayDuKy_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.MSSQLSERVER\MSSQL\DATA\TayDuKy_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [TayDuKy] SET COMPATIBILITY_LEVEL = 140
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [TayDuKy].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [TayDuKy] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [TayDuKy] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [TayDuKy] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [TayDuKy] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [TayDuKy] SET ARITHABORT OFF 
GO
ALTER DATABASE [TayDuKy] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [TayDuKy] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [TayDuKy] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [TayDuKy] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [TayDuKy] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [TayDuKy] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [TayDuKy] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [TayDuKy] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [TayDuKy] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [TayDuKy] SET  DISABLE_BROKER 
GO
ALTER DATABASE [TayDuKy] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [TayDuKy] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [TayDuKy] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [TayDuKy] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [TayDuKy] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [TayDuKy] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [TayDuKy] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [TayDuKy] SET RECOVERY FULL 
GO
ALTER DATABASE [TayDuKy] SET  MULTI_USER 
GO
ALTER DATABASE [TayDuKy] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [TayDuKy] SET DB_CHAINING OFF 
GO
ALTER DATABASE [TayDuKy] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [TayDuKy] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [TayDuKy] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'TayDuKy', N'ON'
GO
ALTER DATABASE [TayDuKy] SET QUERY_STORE = OFF
GO
USE [TayDuKy]
GO
/****** Object:  Table [dbo].[tblActor_Scene]    Script Date: 12/1/2020 9:30:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblActor_Scene](
	[SceneID] [varchar](20) NULL,
	[ActorID] [varchar](20) NULL,
	[Role] [varchar](30) NULL,
	[Script] [varchar](30) NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblActors]    Script Date: 12/1/2020 9:30:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblActors](
	[ActorID] [varchar](20) NOT NULL,
	[Username] [varchar](50) NULL,
	[Fullname] [varchar](50) NULL,
	[Phone] [varchar](50) NULL,
	[Email] [varchar](50) NULL,
	[Image] [varchar](100) NULL,
	[Description] [varchar](50) NULL,
 CONSTRAINT [PK__Actors__4FB645DA94DE9672] PRIMARY KEY CLUSTERED 
(
	[ActorID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
 CONSTRAINT [UQ__Actors__536C85E4D178354E] UNIQUE NONCLUSTERED 
(
	[Username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblDirectors]    Script Date: 12/1/2020 9:30:14 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblDirectors](
	[DirectorID] [varchar](30) NOT NULL,
	[Username] [varchar](50) NULL,
	[Fullname] [varchar](50) NULL,
	[Phone] [varchar](50) NULL,
	[Email] [varchar](50) NULL,
 CONSTRAINT [PK__tblDirec__26C69E26414CDB38] PRIMARY KEY CLUSTERED 
(
	[DirectorID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
 CONSTRAINT [UQ__tblDirec__536C85E417D5E1D1] UNIQUE NONCLUSTERED 
(
	[Username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblLogin]    Script Date: 12/1/2020 9:30:14 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblLogin](
	[Username] [varchar](50) NOT NULL,
	[Password] [varchar](50) NULL,
	[Fullname] [varchar](50) NULL,
	[Role] [varchar](50) NULL,
 CONSTRAINT [PK_Login] PRIMARY KEY CLUSTERED 
(
	[Username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblProps]    Script Date: 12/1/2020 9:30:14 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblProps](
	[PropID] [varchar](20) NOT NULL,
	[PropName] [varchar](50) NULL,
	[Image] [varchar](100) NULL,
	[Quantities] [int] NULL,
	[Available] [bit] NULL,
	[Description] [varchar](50) NULL,
 CONSTRAINT [PK__Props__965F3DDF26F56D9D] PRIMARY KEY CLUSTERED 
(
	[PropID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblProps_Scene]    Script Date: 12/1/2020 9:30:14 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblProps_Scene](
	[PropID] [varchar](20) NOT NULL,
	[SceneID] [varchar](20) NULL,
	[Quantities] [int] NULL,
	[DateFrom] [date] NULL,
	[DateTo] [date] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblScenes]    Script Date: 12/1/2020 9:30:14 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblScenes](
	[SceneID] [varchar](20) NOT NULL,
	[SceneName] [varchar](50) NULL,
	[Location] [varchar](50) NULL,
	[DateFrom] [date] NULL,
	[DateTo] [date] NULL,
	[TotalCuts] [int] NULL,
	[TotalProps] [int] NULL,
	[Description] [varchar](200) NULL,
	[DirectorID] [varchar](30) NULL,
 CONSTRAINT [PK__Film__3214EC27CB63C664] PRIMARY KEY CLUSTERED 
(
	[SceneID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[tblActor_Scene]  WITH CHECK ADD  CONSTRAINT [FK_tblActor_Scene_tblActors] FOREIGN KEY([ActorID])
REFERENCES [dbo].[tblActors] ([ActorID])
GO
ALTER TABLE [dbo].[tblActor_Scene] CHECK CONSTRAINT [FK_tblActor_Scene_tblActors]
GO
ALTER TABLE [dbo].[tblActor_Scene]  WITH CHECK ADD  CONSTRAINT [FK_tblActor_Scene_tblScenes] FOREIGN KEY([SceneID])
REFERENCES [dbo].[tblScenes] ([SceneID])
GO
ALTER TABLE [dbo].[tblActor_Scene] CHECK CONSTRAINT [FK_tblActor_Scene_tblScenes]
GO
ALTER TABLE [dbo].[tblActors]  WITH CHECK ADD  CONSTRAINT [FK__Actors__Username__656C112C] FOREIGN KEY([Username])
REFERENCES [dbo].[tblLogin] ([Username])
GO
ALTER TABLE [dbo].[tblActors] CHECK CONSTRAINT [FK__Actors__Username__656C112C]
GO
ALTER TABLE [dbo].[tblDirectors]  WITH CHECK ADD  CONSTRAINT [FK__tblDirect__Usern__25518C17] FOREIGN KEY([Username])
REFERENCES [dbo].[tblLogin] ([Username])
GO
ALTER TABLE [dbo].[tblDirectors] CHECK CONSTRAINT [FK__tblDirect__Usern__25518C17]
GO
ALTER TABLE [dbo].[tblProps_Scene]  WITH CHECK ADD  CONSTRAINT [FK_Props_Scene_tblProps] FOREIGN KEY([PropID])
REFERENCES [dbo].[tblProps] ([PropID])
GO
ALTER TABLE [dbo].[tblProps_Scene] CHECK CONSTRAINT [FK_Props_Scene_tblProps]
GO
ALTER TABLE [dbo].[tblProps_Scene]  WITH CHECK ADD  CONSTRAINT [FK_Props_Scene_tblScene] FOREIGN KEY([SceneID])
REFERENCES [dbo].[tblScenes] ([SceneID])
GO
ALTER TABLE [dbo].[tblProps_Scene] CHECK CONSTRAINT [FK_Props_Scene_tblScene]
GO
ALTER TABLE [dbo].[tblScenes]  WITH CHECK ADD  CONSTRAINT [FK_tblScene_tblDirectors] FOREIGN KEY([DirectorID])
REFERENCES [dbo].[tblDirectors] ([DirectorID])
GO
ALTER TABLE [dbo].[tblScenes] CHECK CONSTRAINT [FK_tblScene_tblDirectors]
GO
USE [master]
GO
ALTER DATABASE [TayDuKy] SET  READ_WRITE 
GO
