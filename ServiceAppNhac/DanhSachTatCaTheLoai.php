<?php
	require "connect.php";
	$queryTheLoai = "SELECT *FROM theloai";
	$dataTheLoai= mysqli_query($con,$queryTheLoai);
	$arrayTheLoai=array();
	class TheLoai{
		function TheLoai($idTheLoai,$idChuDe, $tenTheLoai, $hinhTheLoai){
			$this->IdTheLoai =$idTheLoai;
			$this->IdChuDe =$idChuDe;
			$this->TenTheLoai=$tenTheLoai;
			$this->HinhTheLoai=$hinhTheLoai;
		}
	}
	while ($row = mysqli_fetch_assoc($dataTheLoai)) {
		array_push($arrayTheLoai, new TheLoai($row['IdTheLoai'],
											$row['IdChuDe'],
											$row['TenTheLoai'],
											$row['HinhTheLoai']));
	}
	echo json_encode($arrayTheLoai);
?>