<?php
	require "connect.php";
	$queryTheLoai = "SELECT DISTINCT *FROM theloai ORDER BY rand(".date("Ymd"). ") LIMIT 4";
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
											$row['HinhTheLoai']);
	}

	$queryChuDe = "SELECT DISTINCT *FROM chude ORDER BY rand(".date("Ymd"). ") LIMIT 4";
	$dataChuDe= mysqli_query($con,$queryChuDe);
	$arrayChuDe=array()
	class ChuDe{
		funtion ChuDe($idChuDe,$tenChuDe,$hinhChuDe){
			$this->IdChuDe =$idChuDe;
			$this->TenChuDe =$tenChuDe;
			$this->HinhChuDe=$hinhChuDe;
		}
	}
	while ($row = mysqli_fetch_assoc($dataChuDe)) {
		array_push($arrayChuDe, new ChuDe($row['IdChuDe'],
											$row['TenChuDe'],
											$row['HinhChuDe']));
	}
	// $arrayChuDevaTheLoai =array("TheLoai"=>$arrayTheLoai,'ChuDe'=>$arrayChuDe);
	// echo json_encode($arrayChuDevaTheLoai);
	echo json_encode($arrayChuDe);
?>