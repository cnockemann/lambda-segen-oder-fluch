package de.viadee.lambda.codeexamples.lesbarkeit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Test;

public class QueryCollections2 extends QueryCollections1 {

	@Test
	@Override
	public void testGroupAlbumTracksByNumber() {
		// Merge tracks from all albums
		List<Track> allTracks = allTracksFromAllAlbums(albums);
		assertEquals(8, allTracks.size());
		assertTrue(allTracks.containsAll(tracks));
		
		// Group album tracks by number
		Map<Integer, List<Track>> tracksByNumber = groupTracksByNumer(allTracks);
		assertTracksByNumber(tracksByNumber);
	}

	protected Map<Integer, List<Track>> groupTracksByNumer(List<Track> allTracks) {
		return allTracks.stream().collect(
				Collectors.groupingBy(Track::getNumber));
	}

	private List<Track> allTracksFromAllAlbums(List<Album> albums) {
		return albums.stream().flatMap(album -> album.getTracks().stream()).collect(Collectors.toList());
	}

}
