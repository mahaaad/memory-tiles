package memorytiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class resetdata {
	public static void main(String args[]) throws FileNotFoundException
	{
		changeNumTiles();
		changeMostTiles();
		addMistakes();
		addLevelsPlayed();
		addLevelsFailed();
		averageTime();
	}

	public static void changeNumTiles() throws FileNotFoundException
	{
		List<String> lines = Arrays.asList(Integer.toString(5));
		Path file = Paths.get("src/userdata/numTiles.txt");
		try {
			Files.write(file, lines, StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void changeMostTiles() throws FileNotFoundException
	{
		List<String> lines = Arrays.asList(Integer.toString(0));
		Path file2 = Paths.get("src/userdata/mostTiles.txt");
		try {
			Files.write(file2, lines, StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void addMistakes() throws FileNotFoundException
	{
		List<String> lines = Arrays.asList(Integer.toString(0));
		Path file2 = Paths.get("src/userdata/mistakes.txt");
		try {
			Files.write(file2, lines, StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void addLevelsPlayed() throws FileNotFoundException
	{
		List<String> lines = Arrays.asList(Integer.toString(0));
		Path file2 = Paths.get("src/userdata/levelsplayed.txt");
		try {
			Files.write(file2, lines, StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void addLevelsFailed() throws FileNotFoundException
	{
		List<String> lines = Arrays.asList(Integer.toString(0));
		Path file2 = Paths.get("src/userdata/levelsfailed.txt");
		try {
			Files.write(file2, lines, StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void averageTime() throws FileNotFoundException
	{
		List<String> lines = Arrays.asList(Integer.toString((0)));
		Path file2 = Paths.get("src/userdata/avrgtime.txt");
		try {
			Files.write(file2, lines, StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
