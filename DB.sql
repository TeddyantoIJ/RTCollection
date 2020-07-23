USE [master]
GO
/****** Object:  Database [RTCollection]    Script Date: 23/07/2020 09:25:36 ******/
CREATE DATABASE [RTCollection]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'RTCollection', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL13.MSSQLSERVER\MSSQL\DATA\RTCollection.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'RTCollection_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL13.MSSQLSERVER\MSSQL\DATA\RTCollection_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [RTCollection] SET COMPATIBILITY_LEVEL = 130
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [RTCollection].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [RTCollection] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [RTCollection] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [RTCollection] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [RTCollection] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [RTCollection] SET ARITHABORT OFF 
GO
ALTER DATABASE [RTCollection] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [RTCollection] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [RTCollection] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [RTCollection] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [RTCollection] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [RTCollection] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [RTCollection] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [RTCollection] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [RTCollection] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [RTCollection] SET  ENABLE_BROKER 
GO
ALTER DATABASE [RTCollection] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [RTCollection] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [RTCollection] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [RTCollection] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [RTCollection] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [RTCollection] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [RTCollection] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [RTCollection] SET RECOVERY FULL 
GO
ALTER DATABASE [RTCollection] SET  MULTI_USER 
GO
ALTER DATABASE [RTCollection] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [RTCollection] SET DB_CHAINING OFF 
GO
ALTER DATABASE [RTCollection] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [RTCollection] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [RTCollection] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [RTCollection] SET QUERY_STORE = OFF
GO
USE [RTCollection]
GO
ALTER DATABASE SCOPED CONFIGURATION SET LEGACY_CARDINALITY_ESTIMATION = OFF;
GO
ALTER DATABASE SCOPED CONFIGURATION SET MAXDOP = 0;
GO
ALTER DATABASE SCOPED CONFIGURATION SET PARAMETER_SNIFFING = ON;
GO
ALTER DATABASE SCOPED CONFIGURATION SET QUERY_OPTIMIZER_HOTFIXES = OFF;
GO
USE [RTCollection]
GO
/****** Object:  Table [dbo].[Barang]    Script Date: 23/07/2020 09:25:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Barang](
	[b_id] [int] NOT NULL,
	[jb_id] [int] NULL,
	[b_nama] [varchar](25) NULL,
	[b_bahan] [varchar](25) NULL,
	[b_ukuran] [varchar](5) NULL,
	[b_stok] [int] NULL,
	[b_harga_satuan] [money] NULL,
	[b_harga_kodian] [money] NULL,
	[b_harga_jual_satuan] [money] NULL,
	[b_harga_jual_kodian] [money] NULL,
	[pms_id] [int] NULL,
 CONSTRAINT [PK_Barang] PRIMARY KEY CLUSTERED 
(
	[b_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DetailPemasokkan]    Script Date: 23/07/2020 09:25:37 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DetailPemasokkan](
	[pmsk_id] [varchar](13) NOT NULL,
	[b_id] [int] NOT NULL,
	[detail_jumlah_barang] [int] NULL,
	[detail_jumlah_kodi] [int] NULL,
	[detail_harga] [money] NULL,
 CONSTRAINT [PK_DetailPemasokkan] PRIMARY KEY CLUSTERED 
(
	[pmsk_id] ASC,
	[b_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Karyawan]    Script Date: 23/07/2020 09:25:37 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Karyawan](
	[kry_id] [varchar](12) NOT NULL,
	[kry_nama] [varchar](25) NULL,
	[kry_no_hp] [varchar](13) NULL,
	[kry_email] [varchar](30) NULL,
	[kry_tgl_lahir] [date] NULL,
	[alamat] [varchar](100) NULL,
	[kry_total_transaksi] [int] NULL,
	[kry_username] [varchar](25) NULL,
	[kry_password] [varchar](25) NULL,
	[kry_jabatan] [varchar](10) NULL,
 CONSTRAINT [PK__Karyawan__CD55C2457D72FF50] PRIMARY KEY CLUSTERED 
(
	[kry_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Pemasok]    Script Date: 23/07/2020 09:25:37 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Pemasok](
	[pms_id] [int] NOT NULL,
	[pms_nama] [varchar](25) NULL,
	[pms_alamat] [varchar](100) NULL,
	[pms_no_hp] [varchar](13) NULL,
	[pms_jumlah_transaksi] [int] NULL,
	[pms_uang_transaksi] [money] NULL,
	[pms_total_hutang] [money] NULL,
 CONSTRAINT [PK_Pemasok] PRIMARY KEY CLUSTERED 
(
	[pms_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Pemasokkan]    Script Date: 23/07/2020 09:25:37 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Pemasokkan](
	[pmsk_id] [varchar](13) NOT NULL,
	[pms_id] [int] NULL,
	[pmsk_tgl_transaksi] [date] NULL,
	[pmsk_jumlah_barang] [int] NULL,
	[pmsk_jumlah_kodi] [int] NULL,
	[pmsk_total_uang] [money] NULL,
	[pmsk_uang_dibayar] [money] NULL,
	[kry_id] [varchar](12) NULL,
	[status] [varchar](25) NULL,
 CONSTRAINT [PK_Pemasokkan] PRIMARY KEY CLUSTERED 
(
	[pmsk_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  View [dbo].[BarangMasuk]    Script Date: 23/07/2020 09:25:37 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE view [dbo].[BarangMasuk] as
select distinct pmsk.pmsk_id,pms.pms_nama, pmsk.pmsk_tgl_transaksi, pmsk.pmsk_jumlah_kodi, pmsk.pmsk_total_uang, pmsk.status from Pemasokkan pmsk
inner join DetailPemasokkan dpmsk on pmsk.pmsk_id = dpmsk.pmsk_id
inner join Pemasok pms on pms.pms_id = pmsk.pms_id
inner join Barang b on b.b_id = dpmsk.b_id
inner join Karyawan k on k.kry_id = pmsk.kry_id
GO
/****** Object:  View [dbo].[DetailBarangMasuk]    Script Date: 23/07/2020 09:25:37 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE view [dbo].[DetailBarangMasuk] as
select pmsk.pmsk_id, b.b_nama, b.b_ukuran, dpmsk.detail_jumlah_kodi, dpmsk.detail_harga from Pemasokkan pmsk
inner join DetailPemasokkan dpmsk on pmsk.pmsk_id = dpmsk.pmsk_id
inner join Pemasok pms on pms.pms_id = pmsk.pms_id
inner join Barang b on b.b_id = dpmsk.b_id
inner join Karyawan k on k.kry_id = pmsk.kry_id
GO
/****** Object:  Table [dbo].[Pelanggan]    Script Date: 23/07/2020 09:25:37 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Pelanggan](
	[pgn_id] [int] NOT NULL,
	[pgn_nama] [varchar](25) NULL,
	[pgn_nama_toko] [varchar](25) NULL,
	[pgn_no_hp] [varchar](13) NULL,
	[pgn_email] [varchar](30) NULL,
	[pgn_alamat] [varchar](100) NULL,
	[pgn_nama_pasar] [varchar](25) NULL,
	[pgn_jumlah_transaksi] [int] NULL,
	[pgn_uang_transaksi] [money] NULL,
	[pgn_total_hutang] [money] NULL,
 CONSTRAINT [PK__Pelangga__A08A15952A61D5BD] PRIMARY KEY CLUSTERED 
(
	[pgn_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Pemesanan]    Script Date: 23/07/2020 09:25:37 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Pemesanan](
	[pmsn_id] [varchar](13) NOT NULL,
	[pmsn_tgl_transaksi] [date] NULL,
	[pgn_id] [int] NULL,
	[pmsn_jumlah_barang] [int] NULL,
	[pmsn_jumlah_kodi] [int] NULL,
	[pmsn_total_uang] [money] NULL,
	[pmsn_status] [varchar](20) NULL,
	[kry_id] [varchar](12) NULL,
 CONSTRAINT [PK_Pemesanan] PRIMARY KEY CLUSTERED 
(
	[pmsn_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  View [dbo].[BarangKeluar]    Script Date: 23/07/2020 09:25:37 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE view [dbo].[BarangKeluar] as
select pmsn.pmsn_id, pgn.pgn_nama, pgn.pgn_nama_toko,pgn.pgn_nama_pasar, pmsn.pmsn_tgl_transaksi, pmsn.pmsn_jumlah_kodi, pmsn.pmsn_total_uang, pmsn.pmsn_status from Pemesanan pmsn
inner join Pelanggan pgn on pgn.pgn_id = pmsn.pgn_id
GO
/****** Object:  Table [dbo].[DetailPemesanan]    Script Date: 23/07/2020 09:25:37 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DetailPemesanan](
	[pmsn_id] [varchar](13) NOT NULL,
	[b_id] [int] NOT NULL,
	[detail_jumlah_barang] [int] NULL,
	[detail_jumlah_kodi] [int] NULL,
	[detail_total_uang] [money] NULL,
 CONSTRAINT [PK_DetailPemesanan] PRIMARY KEY CLUSTERED 
(
	[pmsn_id] ASC,
	[b_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  View [dbo].[DetailBarangKeluar]    Script Date: 23/07/2020 09:25:37 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE view [dbo].[DetailBarangKeluar] as
select dpms.pmsn_id, b.b_nama, b.b_ukuran, dpms.detail_jumlah_kodi, dpms.detail_total_uang from DetailPemesanan dpms
inner join Barang b on b.b_id = dpms.b_id
GO
/****** Object:  Table [dbo].[Karung]    Script Date: 23/07/2020 09:25:37 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Karung](
	[krg_id] [int] NOT NULL,
	[pgrm_id] [varchar](13) NULL,
	[krg_jumlah_kodi] [int] NULL,
	[krg_jumlah_jenis_barang] [int] NULL,
	[keterangan] [varchar](50) NULL,
 CONSTRAINT [PK_Karung] PRIMARY KEY CLUSTERED 
(
	[krg_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  View [dbo].[KarungPengiriman]    Script Date: 23/07/2020 09:25:37 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE view [dbo].[KarungPengiriman] as
select kr.krg_id, kr.keterangan, kr.krg_jumlah_kodi, kr.krg_jumlah_jenis_barang from Karung kr
GO
/****** Object:  Table [dbo].[DetailKarung]    Script Date: 23/07/2020 09:25:37 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DetailKarung](
	[krg_id] [int] NOT NULL,
	[b_id] [int] NOT NULL,
	[detail_jumlah_barang] [int] NULL,
	[detail_jumlah_kodi] [int] NULL,
 CONSTRAINT [PK_DetailKarung] PRIMARY KEY CLUSTERED 
(
	[krg_id] ASC,
	[b_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  View [dbo].[DetailKarungPengiriman]    Script Date: 23/07/2020 09:25:37 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create view [dbo].[DetailKarungPengiriman] as
select b.b_nama, b.b_ukuran, dkr.detail_jumlah_kodi from Karung kr
inner join DetailKarung dkr on dkr.krg_id = kr.krg_id
inner join Barang b on b.b_id = dkr.b_id
GO
/****** Object:  Table [dbo].[BayarPemasok]    Script Date: 23/07/2020 09:25:37 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BayarPemasok](
	[Bpmks_id] [varchar](13) NOT NULL,
	[pms_id] [int] NULL,
	[bpmks_tgl_transaksi] [date] NULL,
	[bpmks_uang_dibayar] [money] NULL,
 CONSTRAINT [PK_BayarPemasok] PRIMARY KEY CLUSTERED 
(
	[Bpmks_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DetailPengiriman]    Script Date: 23/07/2020 09:25:37 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DetailPengiriman](
	[pmsn_id] [varchar](13) NOT NULL,
	[pgrm_id] [varchar](13) NOT NULL,
 CONSTRAINT [PK_DetailPengiriman] PRIMARY KEY CLUSTERED 
(
	[pmsn_id] ASC,
	[pgrm_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[JenisBarang]    Script Date: 23/07/2020 09:25:37 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[JenisBarang](
	[jb_id] [int] NOT NULL,
	[jb_nama] [varchar](25) NULL,
PRIMARY KEY CLUSTERED 
(
	[jb_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Pembayaran]    Script Date: 23/07/2020 09:25:37 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Pembayaran](
	[pmby_id] [varchar](13) NOT NULL,
	[pgn_id] [int] NULL,
	[kry_id] [varchar](12) NULL,
	[pmby_tgl_transaksi] [date] NULL,
	[pmby_uang_masuk] [money] NULL,
 CONSTRAINT [PK_Pembayaran] PRIMARY KEY CLUSTERED 
(
	[pmby_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Pengiriman]    Script Date: 23/07/2020 09:25:37 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Pengiriman](
	[pgrm_id] [varchar](13) NOT NULL,
	[pgrm_tgl_transaksi] [date] NULL,
	[pgrm_jumlah_barang] [int] NULL,
	[pgrm_jumlah_kodi] [int] NULL,
	[pgrm_jumlah_karung] [int] NULL,
	[kry_id] [varchar](12) NULL,
	[status] [varchar](50) NULL,
 CONSTRAINT [PK_Pengiriman] PRIMARY KEY CLUSTERED 
(
	[pgrm_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Barang] ([b_id], [jb_id], [b_nama], [b_bahan], [b_ukuran], [b_stok], [b_harga_satuan], [b_harga_kodian], [b_harga_jual_satuan], [b_harga_jual_kodian], [pms_id]) VALUES (1, 1, N'Kiky Jenas', N'Katun', N'30', 24, 250000.0000, 5000000.0000, 270000.0000, 5200000.0000, 1)
INSERT [dbo].[Barang] ([b_id], [jb_id], [b_nama], [b_bahan], [b_ukuran], [b_stok], [b_harga_satuan], [b_harga_kodian], [b_harga_jual_satuan], [b_harga_jual_kodian], [pms_id]) VALUES (2, 1, N'Kiky Jenas', N'Katun', N'31', 16, 250000.0000, 5000000.0000, 270000.0000, 5200000.0000, 1)
INSERT [dbo].[Barang] ([b_id], [jb_id], [b_nama], [b_bahan], [b_ukuran], [b_stok], [b_harga_satuan], [b_harga_kodian], [b_harga_jual_satuan], [b_harga_jual_kodian], [pms_id]) VALUES (3, 1, N'Kiky Jenas', N'Katun', N'32', 10, 250000.0000, 5000000.0000, 270000.0000, 5200000.0000, 1)
INSERT [dbo].[Barang] ([b_id], [jb_id], [b_nama], [b_bahan], [b_ukuran], [b_stok], [b_harga_satuan], [b_harga_kodian], [b_harga_jual_satuan], [b_harga_jual_kodian], [pms_id]) VALUES (4, 1, N'Kiky Jenas', N'Katun', N'29', 10, 250000.0000, 5000000.0000, 270000.0000, 5200000.0000, 1)
INSERT [dbo].[Barang] ([b_id], [jb_id], [b_nama], [b_bahan], [b_ukuran], [b_stok], [b_harga_satuan], [b_harga_kodian], [b_harga_jual_satuan], [b_harga_jual_kodian], [pms_id]) VALUES (5, 1, N'Kiky Jenas', N'Katun', N'28', 14, 250000.0000, 5000000.0000, 270000.0000, 5200000.0000, 1)
INSERT [dbo].[Barang] ([b_id], [jb_id], [b_nama], [b_bahan], [b_ukuran], [b_stok], [b_harga_satuan], [b_harga_kodian], [b_harga_jual_satuan], [b_harga_jual_kodian], [pms_id]) VALUES (6, 1, N'Testing', N'Katoen', N'28', 10, 100000.0000, 2000000.0000, 120000.0000, 2500000.0000, 1)
INSERT [dbo].[DetailPemasokkan] ([pmsk_id], [b_id], [detail_jumlah_barang], [detail_jumlah_kodi], [detail_harga]) VALUES (N'0001', 1, 100, 5, 25000000.0000)
INSERT [dbo].[DetailPemasokkan] ([pmsk_id], [b_id], [detail_jumlah_barang], [detail_jumlah_kodi], [detail_harga]) VALUES (N'0001', 2, 60, 3, 5000000.0000)
INSERT [dbo].[DetailPemasokkan] ([pmsk_id], [b_id], [detail_jumlah_barang], [detail_jumlah_kodi], [detail_harga]) VALUES (N'0001', 5, 80, 4, 20000000.0000)
INSERT [dbo].[DetailPemasokkan] ([pmsk_id], [b_id], [detail_jumlah_barang], [detail_jumlah_kodi], [detail_harga]) VALUES (N'0002', 2, 80, 4, 20000000.0000)
INSERT [dbo].[DetailPemasokkan] ([pmsk_id], [b_id], [detail_jumlah_barang], [detail_jumlah_kodi], [detail_harga]) VALUES (N'0002', 3, 20, 1, 5000000.0000)
INSERT [dbo].[DetailPemasokkan] ([pmsk_id], [b_id], [detail_jumlah_barang], [detail_jumlah_kodi], [detail_harga]) VALUES (N'0003', 1, 60, 3, 15000000.0000)
INSERT [dbo].[DetailPemasokkan] ([pmsk_id], [b_id], [detail_jumlah_barang], [detail_jumlah_kodi], [detail_harga]) VALUES (N'0003', 2, 60, 3, 15000000.0000)
INSERT [dbo].[DetailPemasokkan] ([pmsk_id], [b_id], [detail_jumlah_barang], [detail_jumlah_kodi], [detail_harga]) VALUES (N'13072020-0004', 1, 40, 2, 10000000.0000)
INSERT [dbo].[DetailPemasokkan] ([pmsk_id], [b_id], [detail_jumlah_barang], [detail_jumlah_kodi], [detail_harga]) VALUES (N'13072020-0005', 1, 80, 4, 20000000.0000)
INSERT [dbo].[DetailPemesanan] ([pmsn_id], [b_id], [detail_jumlah_barang], [detail_jumlah_kodi], [detail_total_uang]) VALUES (N'20200711-0001', 3, 100, 5, 25000000.0000)
INSERT [dbo].[DetailPemesanan] ([pmsn_id], [b_id], [detail_jumlah_barang], [detail_jumlah_kodi], [detail_total_uang]) VALUES (N'20200713-0002', 1, 100, 5, 25000000.0000)
INSERT [dbo].[DetailPemesanan] ([pmsn_id], [b_id], [detail_jumlah_barang], [detail_jumlah_kodi], [detail_total_uang]) VALUES (N'20200713-0003', 1, 60, 3, 5000000.0000)
INSERT [dbo].[DetailPemesanan] ([pmsn_id], [b_id], [detail_jumlah_barang], [detail_jumlah_kodi], [detail_total_uang]) VALUES (N'20200714-0004', 1, 980, 49, 245000000.0000)
INSERT [dbo].[JenisBarang] ([jb_id], [jb_nama]) VALUES (1, N'Celana Jeans')
INSERT [dbo].[JenisBarang] ([jb_id], [jb_nama]) VALUES (2, N'Boxer')
INSERT [dbo].[Karyawan] ([kry_id], [kry_nama], [kry_no_hp], [kry_email], [kry_tgl_lahir], [alamat], [kry_total_transaksi], [kry_username], [kry_password], [kry_jabatan]) VALUES (N'202006290001', N'Aqilah Rahma Tsabitah', N'0877661155323', N'aqilah.rtsabitah@gmail.com', CAST(N'2005-09-26' AS Date), N'Kauman Comal', 0, N'-', N'-', N'Pengemudi')
INSERT [dbo].[Karyawan] ([kry_id], [kry_nama], [kry_no_hp], [kry_email], [kry_tgl_lahir], [alamat], [kry_total_transaksi], [kry_username], [kry_password], [kry_jabatan]) VALUES (N'202007010002', N'Kiky Rahmawati Sitombingg', N'0817273771773', N'KikyCants@gmail.com', CAST(N'2000-05-17' AS Date), N'Papanggo hehehe', 0, N'Kikis', N'Kiky123', N'Kasir')
INSERT [dbo].[Pelanggan] ([pgn_id], [pgn_nama], [pgn_nama_toko], [pgn_no_hp], [pgn_email], [pgn_alamat], [pgn_nama_pasar], [pgn_jumlah_transaksi], [pgn_uang_transaksi], [pgn_total_hutang]) VALUES (1, N'Mank Ujang', N'Ujang Sport', N'087832724931', N'ujang@gmail.com', N'Jakarta Utara  Japat', N'Tanah Abang', 0, 0.0000, 0.0000)
INSERT [dbo].[Pemasok] ([pms_id], [pms_nama], [pms_alamat], [pms_no_hp], [pms_jumlah_transaksi], [pms_uang_transaksi], [pms_total_hutang]) VALUES (1, N'Teddyanto IJ', N'MUARA INTA', N'0888888888888', 2, 0.0000, 30000000.0000)
INSERT [dbo].[Pemasokkan] ([pmsk_id], [pms_id], [pmsk_tgl_transaksi], [pmsk_jumlah_barang], [pmsk_jumlah_kodi], [pmsk_total_uang], [pmsk_uang_dibayar], [kry_id], [status]) VALUES (N'0001', 1, CAST(N'2020-07-06' AS Date), 240, 12, 5000000.0000, 0.0000, N'202007010002', N'Proses')
INSERT [dbo].[Pemasokkan] ([pmsk_id], [pms_id], [pmsk_tgl_transaksi], [pmsk_jumlah_barang], [pmsk_jumlah_kodi], [pmsk_total_uang], [pmsk_uang_dibayar], [kry_id], [status]) VALUES (N'0002', 1, CAST(N'2020-07-06' AS Date), 100, 5, 25000000.0000, 0.0000, N'202007010002', N'Proses')
INSERT [dbo].[Pemasokkan] ([pmsk_id], [pms_id], [pmsk_tgl_transaksi], [pmsk_jumlah_barang], [pmsk_jumlah_kodi], [pmsk_total_uang], [pmsk_uang_dibayar], [kry_id], [status]) VALUES (N'0003', 1, CAST(N'2020-07-12' AS Date), 120, 6, 30000000.0000, 0.0000, N'202007010002', N'Proses')
INSERT [dbo].[Pemasokkan] ([pmsk_id], [pms_id], [pmsk_tgl_transaksi], [pmsk_jumlah_barang], [pmsk_jumlah_kodi], [pmsk_total_uang], [pmsk_uang_dibayar], [kry_id], [status]) VALUES (N'13072020-0004', 1, CAST(N'2020-07-13' AS Date), 40, 2, 10000000.0000, 0.0000, N'202007010002', N'Sukses')
INSERT [dbo].[Pemasokkan] ([pmsk_id], [pms_id], [pmsk_tgl_transaksi], [pmsk_jumlah_barang], [pmsk_jumlah_kodi], [pmsk_total_uang], [pmsk_uang_dibayar], [kry_id], [status]) VALUES (N'13072020-0005', 1, CAST(N'2020-07-13' AS Date), 80, 4, 20000000.0000, 0.0000, N'202007010002', N'Sukses')
INSERT [dbo].[Pemesanan] ([pmsn_id], [pmsn_tgl_transaksi], [pgn_id], [pmsn_jumlah_barang], [pmsn_jumlah_kodi], [pmsn_total_uang], [pmsn_status], [kry_id]) VALUES (N'20200711-0001', CAST(N'2020-07-11' AS Date), 1, 100, 5, 25000000.0000, N'Disiapkan', N'202007010002')
INSERT [dbo].[Pemesanan] ([pmsn_id], [pmsn_tgl_transaksi], [pgn_id], [pmsn_jumlah_barang], [pmsn_jumlah_kodi], [pmsn_total_uang], [pmsn_status], [kry_id]) VALUES (N'20200713-0002', CAST(N'2020-07-13' AS Date), 1, 100, 5, 25000000.0000, N'Disiapkan', N'202007010002')
INSERT [dbo].[Pemesanan] ([pmsn_id], [pmsn_tgl_transaksi], [pgn_id], [pmsn_jumlah_barang], [pmsn_jumlah_kodi], [pmsn_total_uang], [pmsn_status], [kry_id]) VALUES (N'20200713-0003', CAST(N'2020-07-13' AS Date), 1, 60, 3, 5000000.0000, N'Disiapkan', N'202007010002')
INSERT [dbo].[Pemesanan] ([pmsn_id], [pmsn_tgl_transaksi], [pgn_id], [pmsn_jumlah_barang], [pmsn_jumlah_kodi], [pmsn_total_uang], [pmsn_status], [kry_id]) VALUES (N'20200714-0004', CAST(N'2020-07-14' AS Date), 1, 980, 49, 245000000.0000, N'Disiapkan', N'202007010002')
ALTER TABLE [dbo].[Barang]  WITH CHECK ADD  CONSTRAINT [FK_Barang_JenisBarang] FOREIGN KEY([jb_id])
REFERENCES [dbo].[JenisBarang] ([jb_id])
GO
ALTER TABLE [dbo].[Barang] CHECK CONSTRAINT [FK_Barang_JenisBarang]
GO
ALTER TABLE [dbo].[Barang]  WITH CHECK ADD  CONSTRAINT [FK_Barang_Pemasok] FOREIGN KEY([pms_id])
REFERENCES [dbo].[Pemasok] ([pms_id])
GO
ALTER TABLE [dbo].[Barang] CHECK CONSTRAINT [FK_Barang_Pemasok]
GO
ALTER TABLE [dbo].[BayarPemasok]  WITH CHECK ADD  CONSTRAINT [FK_BayarPemasok_Pemasok] FOREIGN KEY([pms_id])
REFERENCES [dbo].[Pemasok] ([pms_id])
GO
ALTER TABLE [dbo].[BayarPemasok] CHECK CONSTRAINT [FK_BayarPemasok_Pemasok]
GO
ALTER TABLE [dbo].[DetailKarung]  WITH CHECK ADD  CONSTRAINT [FK_DetailKarung_Barang] FOREIGN KEY([b_id])
REFERENCES [dbo].[Barang] ([b_id])
GO
ALTER TABLE [dbo].[DetailKarung] CHECK CONSTRAINT [FK_DetailKarung_Barang]
GO
ALTER TABLE [dbo].[DetailKarung]  WITH CHECK ADD  CONSTRAINT [FK_DetailKarung_Karung] FOREIGN KEY([krg_id])
REFERENCES [dbo].[Karung] ([krg_id])
GO
ALTER TABLE [dbo].[DetailKarung] CHECK CONSTRAINT [FK_DetailKarung_Karung]
GO
ALTER TABLE [dbo].[DetailPemasokkan]  WITH CHECK ADD  CONSTRAINT [FK_DetailPemasokkan_Barang] FOREIGN KEY([b_id])
REFERENCES [dbo].[Barang] ([b_id])
GO
ALTER TABLE [dbo].[DetailPemasokkan] CHECK CONSTRAINT [FK_DetailPemasokkan_Barang]
GO
ALTER TABLE [dbo].[DetailPemasokkan]  WITH CHECK ADD  CONSTRAINT [FK_DetailPemasokkan_Pemasokkan] FOREIGN KEY([pmsk_id])
REFERENCES [dbo].[Pemasokkan] ([pmsk_id])
GO
ALTER TABLE [dbo].[DetailPemasokkan] CHECK CONSTRAINT [FK_DetailPemasokkan_Pemasokkan]
GO
ALTER TABLE [dbo].[DetailPemesanan]  WITH CHECK ADD  CONSTRAINT [FK_DetailPemesanan_Barang] FOREIGN KEY([b_id])
REFERENCES [dbo].[Barang] ([b_id])
GO
ALTER TABLE [dbo].[DetailPemesanan] CHECK CONSTRAINT [FK_DetailPemesanan_Barang]
GO
ALTER TABLE [dbo].[DetailPemesanan]  WITH CHECK ADD  CONSTRAINT [FK_DetailPemesanan_DetailPemesanan] FOREIGN KEY([pmsn_id])
REFERENCES [dbo].[Pemesanan] ([pmsn_id])
GO
ALTER TABLE [dbo].[DetailPemesanan] CHECK CONSTRAINT [FK_DetailPemesanan_DetailPemesanan]
GO
ALTER TABLE [dbo].[DetailPengiriman]  WITH CHECK ADD  CONSTRAINT [FK_DetailPengiriman_Pemesanan] FOREIGN KEY([pmsn_id])
REFERENCES [dbo].[Pemesanan] ([pmsn_id])
GO
ALTER TABLE [dbo].[DetailPengiriman] CHECK CONSTRAINT [FK_DetailPengiriman_Pemesanan]
GO
ALTER TABLE [dbo].[DetailPengiriman]  WITH CHECK ADD  CONSTRAINT [FK_DetailPengiriman_Pengiriman] FOREIGN KEY([pgrm_id])
REFERENCES [dbo].[Pengiriman] ([pgrm_id])
GO
ALTER TABLE [dbo].[DetailPengiriman] CHECK CONSTRAINT [FK_DetailPengiriman_Pengiriman]
GO
ALTER TABLE [dbo].[Karung]  WITH CHECK ADD  CONSTRAINT [FK_Karung_Pengiriman] FOREIGN KEY([pgrm_id])
REFERENCES [dbo].[Pengiriman] ([pgrm_id])
GO
ALTER TABLE [dbo].[Karung] CHECK CONSTRAINT [FK_Karung_Pengiriman]
GO
ALTER TABLE [dbo].[Pemasokkan]  WITH CHECK ADD FOREIGN KEY([kry_id])
REFERENCES [dbo].[Karyawan] ([kry_id])
GO
ALTER TABLE [dbo].[Pemasokkan]  WITH CHECK ADD  CONSTRAINT [FK_Pemasokkan_Pemasok] FOREIGN KEY([pms_id])
REFERENCES [dbo].[Pemasok] ([pms_id])
GO
ALTER TABLE [dbo].[Pemasokkan] CHECK CONSTRAINT [FK_Pemasokkan_Pemasok]
GO
ALTER TABLE [dbo].[Pembayaran]  WITH CHECK ADD  CONSTRAINT [FK_Pembayaran_Karyawan] FOREIGN KEY([kry_id])
REFERENCES [dbo].[Karyawan] ([kry_id])
GO
ALTER TABLE [dbo].[Pembayaran] CHECK CONSTRAINT [FK_Pembayaran_Karyawan]
GO
ALTER TABLE [dbo].[Pembayaran]  WITH CHECK ADD  CONSTRAINT [FK_Pembayaran_Pelanggan] FOREIGN KEY([pgn_id])
REFERENCES [dbo].[Pelanggan] ([pgn_id])
GO
ALTER TABLE [dbo].[Pembayaran] CHECK CONSTRAINT [FK_Pembayaran_Pelanggan]
GO
ALTER TABLE [dbo].[Pemesanan]  WITH CHECK ADD FOREIGN KEY([kry_id])
REFERENCES [dbo].[Karyawan] ([kry_id])
GO
ALTER TABLE [dbo].[Pemesanan]  WITH CHECK ADD  CONSTRAINT [FK_Pemesanan_Pelanggan] FOREIGN KEY([pgn_id])
REFERENCES [dbo].[Pelanggan] ([pgn_id])
GO
ALTER TABLE [dbo].[Pemesanan] CHECK CONSTRAINT [FK_Pemesanan_Pelanggan]
GO
ALTER TABLE [dbo].[Pengiriman]  WITH CHECK ADD  CONSTRAINT [FK_Pengiriman_Karyawan] FOREIGN KEY([kry_id])
REFERENCES [dbo].[Karyawan] ([kry_id])
GO
ALTER TABLE [dbo].[Pengiriman] CHECK CONSTRAINT [FK_Pengiriman_Karyawan]
GO
USE [master]
GO
ALTER DATABASE [RTCollection] SET  READ_WRITE 
GO
