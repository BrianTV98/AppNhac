<?php
	require("connect.php");
	$query ="SELECT *FROM album ";
	class Album{
		public function Album($IdAlbum,$TenAlBum,$TenCaSiAlbum, $HinhAlBum)
		{
			$this-> idAlbum =$IdAlbum;
			$this-> tenAlBum =$TenAlBum;
			$this-> tenCaSiAlbum =$TenCaSiAlbum;
			$this-> hinhAlBum =$HinhAlBum;
		}
	}
	$arrayAlbum= array();
	$data =mysqli_query($con, $query);
	while($row =mysqli_fetch_assoc($data)) {
		array_push($arrayAlbum, new Album($row['IdAlbum'],
											$row['TenAlBum'],
											$row["TenCaSiAlbum"],
											$row["HinhAlBum"]));

	}
	echo json_encode($arrayAlbum)
?>