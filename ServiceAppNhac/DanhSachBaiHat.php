<?php
	require "connect.php";
	class BaiHat{
		public function BaiHat($IdBaiHat,$IdAlBum, $IdTheLoai, $IdPlayList, $TenBaiHat,$HinhBaiHat,$CaSi,$LinkBaiHat,$LuocThich)
		{
			$this-> idBaiHat =$IdBaiHat;
			$this-> idAlBum =$IdAlBum;
			$this-> idTheLoai =$IdTheLoai;
			$this-> idPhayList =$IdPlayList;
			$this-> tenBaiHat =$TenBaiHat;
			$this-> hinhBaiHat =$HinhBaiHat;
			$this-> caSi =$CaSi;
			$this-> linkBaiHat =$LinkBaiHat;
			$this->luocThich= $LuocThich;

		}
	}

	if(isset($_POST['idptheloai'])){
		$idplaytheloai = $_POST['idptheloai'];
		$query="SELECT * FROM baihat WHERE FIND_IN_SET('$idptheloai',IdTheLoai)";
	}
	if(isset($_POST['idplaylist'])){
		$idplaylist = $_POST['idplaylist'];
		$query="SELECT * FROM baihat WHERE FIND_IN_SET('$idplaylist',IdPlayList)";
	}

	if(isset($_POST['idquangcao'])){
		$idquangcao=$_POST['idquangcao'];
		$queryquangcao="SELECT *FROM quangcao WHERE Id='$idquangcao'";
		$dataquangcao= mysqli_query($con,$queryquangcao);
		$rowquangcao= mysqli_fetch_assoc($dataquangcao);
		$id= $rowquangcao['IdBaiHat']; 
		$query="SELECT *FROM baihat WHERE IdBaiHat=$id";
	}
	

	$data= mysqli_query($con,$query);
	$arrDanhSachBaiHat=array();
	while ($row=mysqli_fetch_assoc($data)) {
		array_push($arrDanhSachBaiHat, new BaiHat($row['IdBaiHat'],
											$row['IdAlBum'],
											$row['IdTheLoai'],
											$row['IdPlayList'],
											$row['TenBaiHat'],
											$row['HinhBaiHat'],
											$row['CaSi'],
											$row['LinkBaiHat'],
											$row['LuocThich']));

	}
	echo json_encode($arrDanhSachBaiHat);
?>