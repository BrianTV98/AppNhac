<?php
	require("connect.php");
	$query ="SELECT *FROM playlist ";
	class DanhSachPlayList{

		public function DanhSachPlayList($idplaylist,$ten,$hinh, $icon)
		{
			$this-> idPlaylist =$idplaylist;
			$this-> Ten =$ten;
			$this-> HinhPlayList =$hinh;
			$this-> Icon =$icon;
		}
	}
	$arrayDanhSachPlayListy= array();
	$data =mysqli_query($con, $query);
	while ($row =mysqli_fetch_assoc($data)) {
		array_push($arrayDanhSachPlayList, new DanhSachPlayList($row['IdPlaylist'],
															$row['Ten'],
															$row['HinhAnh'],
															$row['HinhIcon']));
	}
	echo json_encode($arrayDanhSachPlayList;
?>