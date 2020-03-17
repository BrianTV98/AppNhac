<?php
	require("connect.php");
	$query ="SELECT DISTINCT *FROM playlist ORDER BY rand(" .date("Ymd") .") LIMIT 3";
	class PlayListToday{
		public function PlayListToday($idplaylist,$ten,$hinh, $icon)
		{
			$this-> idPlaylist =$idplaylist;
			$this-> Ten =$ten;
			$this-> HinhPlayList =$hinh;
			$this-> Icon =$icon;
		}
	}
	$arrayPlayListToday= array();
	$data =mysqli_query($con, $query)
	while ($row =mysqli_fetch_assoc($data)) {
		array_push($arrayPlayListToday, new PlayListToday($row['IdPlaylist'],
															$row['Ten'],
															$row["HinhAnh"],
															$row["HinhIcon"]));

	}
	echo json_encode($arrayPlayListToday)
?>