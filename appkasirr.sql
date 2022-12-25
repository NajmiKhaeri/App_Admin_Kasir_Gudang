-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 08 Nov 2022 pada 08.00
-- Versi server: 10.4.24-MariaDB
-- Versi PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `appkasirr`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_barang`
--

CREATE TABLE `tbl_barang` (
  `kd_barang` varchar(15) NOT NULL,
  `nama_barang` varchar(30) NOT NULL,
  `kategori` varchar(15) NOT NULL,
  `harga_jual` int(90) NOT NULL,
  `harga_beli` int(90) NOT NULL,
  `stok` int(30) NOT NULL,
  `NAMA_USER` varchar(30) NOT NULL,
  `ID_USER` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_detailtransaksi`
--

CREATE TABLE `tbl_detailtransaksi` (
  `id_transaksi` bigint(10) NOT NULL,
  `kd_barang` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_level`
--

CREATE TABLE `tbl_level` (
  `ID_LEVEL` int(2) NOT NULL,
  `LEVEL` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tbl_level`
--

INSERT INTO `tbl_level` (`ID_LEVEL`, `LEVEL`) VALUES
(1, 'ADMIN'),
(2, 'GUDANG'),
(3, 'KASIR');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_penjualan`
--

CREATE TABLE `tbl_penjualan` (
  `kd_barang` varchar(15) NOT NULL,
  `nota` varchar(15) NOT NULL,
  `ID_USER` varchar(10) NOT NULL,
  `nama_barang` varchar(30) NOT NULL,
  `harga_jual` int(12) NOT NULL,
  `tgl_penjualan` varchar(20) NOT NULL,
  `jml_barang` int(10) NOT NULL,
  `ttl_bayar` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_suplier`
--

CREATE TABLE `tbl_suplier` (
  `id_suplier` varchar(15) NOT NULL,
  `kd_pembelian` varchar(15) NOT NULL,
  `nama_suplier` varchar(30) NOT NULL,
  `nope_suplier` varchar(15) NOT NULL,
  `alamat` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_transaksi`
--

CREATE TABLE `tbl_transaksi` (
  `id_transaksi` bigint(10) NOT NULL,
  `kode_transaksi` int(11) NOT NULL,
  `nama_kasir` varchar(30) NOT NULL,
  `tanggal` datetime NOT NULL,
  `harga_total` int(10) NOT NULL,
  `jumlah_barang` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_user`
--

CREATE TABLE `tbl_user` (
  `ID_USER` varchar(10) NOT NULL,
  `ID_LEVEL` int(2) NOT NULL,
  `NAMA_USER` varchar(30) NOT NULL,
  `JK` char(1) NOT NULL,
  `TANGGAL_LAHIR` varchar(50) NOT NULL,
  `NOPE` varchar(13) NOT NULL,
  `USERNAME` varchar(50) NOT NULL,
  `PASSWORD` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tbl_user`
--

INSERT INTO `tbl_user` (`ID_USER`, `ID_LEVEL`, `NAMA_USER`, `JK`, `TANGGAL_LAHIR`, `NOPE`, `USERNAME`, `PASSWORD`) VALUES
('USER0001', 1, 'najmi', 'L', '2022-11-10', '0991', 'jbjsb', 'jkdkikd'),
('USER0002', 2, 'najmik', 'L', '2022-11-17', '0999', 'hhh', 'hhh'),
('USER0003', 2, 'najmik', 'L', '2022-11-17', '0999', 'hhh', 'hhh');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `tbl_barang`
--
ALTER TABLE `tbl_barang`
  ADD PRIMARY KEY (`kd_barang`),
  ADD KEY `ID_USER` (`ID_USER`);

--
-- Indeks untuk tabel `tbl_detailtransaksi`
--
ALTER TABLE `tbl_detailtransaksi`
  ADD KEY `id_transaksi` (`id_transaksi`),
  ADD KEY `kd_barang` (`kd_barang`);

--
-- Indeks untuk tabel `tbl_level`
--
ALTER TABLE `tbl_level`
  ADD PRIMARY KEY (`ID_LEVEL`);

--
-- Indeks untuk tabel `tbl_penjualan`
--
ALTER TABLE `tbl_penjualan`
  ADD PRIMARY KEY (`nota`),
  ADD KEY `kd_barang` (`kd_barang`),
  ADD KEY `ID_USER` (`ID_USER`);

--
-- Indeks untuk tabel `tbl_suplier`
--
ALTER TABLE `tbl_suplier`
  ADD PRIMARY KEY (`id_suplier`);

--
-- Indeks untuk tabel `tbl_transaksi`
--
ALTER TABLE `tbl_transaksi`
  ADD PRIMARY KEY (`id_transaksi`);

--
-- Indeks untuk tabel `tbl_user`
--
ALTER TABLE `tbl_user`
  ADD PRIMARY KEY (`ID_USER`),
  ADD KEY `ID_LEVEL` (`ID_LEVEL`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `tbl_level`
--
ALTER TABLE `tbl_level`
  MODIFY `ID_LEVEL` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `tbl_barang`
--
ALTER TABLE `tbl_barang`
  ADD CONSTRAINT `tbl_barang_ibfk_1` FOREIGN KEY (`ID_USER`) REFERENCES `tbl_user` (`ID_USER`);

--
-- Ketidakleluasaan untuk tabel `tbl_detailtransaksi`
--
ALTER TABLE `tbl_detailtransaksi`
  ADD CONSTRAINT `tbl_detailtransaksi_ibfk_1` FOREIGN KEY (`kd_barang`) REFERENCES `tbl_barang` (`kd_barang`),
  ADD CONSTRAINT `tbl_detailtransaksi_ibfk_2` FOREIGN KEY (`id_transaksi`) REFERENCES `tbl_transaksi` (`id_transaksi`);

--
-- Ketidakleluasaan untuk tabel `tbl_penjualan`
--
ALTER TABLE `tbl_penjualan`
  ADD CONSTRAINT `tbl_penjualan_ibfk_1` FOREIGN KEY (`kd_barang`) REFERENCES `tbl_barang` (`kd_barang`),
  ADD CONSTRAINT `tbl_penjualan_ibfk_2` FOREIGN KEY (`ID_USER`) REFERENCES `tbl_user` (`ID_USER`);

--
-- Ketidakleluasaan untuk tabel `tbl_user`
--
ALTER TABLE `tbl_user`
  ADD CONSTRAINT `tbl_user_ibfk_1` FOREIGN KEY (`ID_LEVEL`) REFERENCES `tbl_level` (`ID_LEVEL`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
