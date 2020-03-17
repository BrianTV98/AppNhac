<?php
	require("connect.php");
	$query ="SELECT DISTINCT *FROM baihat ORDER BY LuocThich DESC LIMIT 5";
	clas BaiHat{
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
	$arrayBaiHat= array();
	$dataBaiHat =mysqli_query($con, $query);
	while($row =mysqli_fetch_assoc($dataBaiHat)) {
		array_push($arrayBaiHat, new BaiHat($row['IdBaiHat'],
											$row['IdAlBum'],
											$row['IdTheLoai'],
											$row['IdPlayList'],
											$row['TenBaiHat'],
											$row['HinhBaiHat'],
											$row['CaSi'],
											$row['LinkBaiHat'],
											$row['LuocThich']));

	}
	echo json_encode($arrayBaiHat)
?>