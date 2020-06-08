package pobj.pinboard.editor;

import java.util.ArrayList;
import java.util.List;

import pobj.pinboard.document.Clip;

public class Clipboard {
	private List<Clip> clips; 
	private static Clipboard clipboard = new Clipboard();
	private List<ClipboardListener> cible;
	
	private Clipboard () {
		clips = new ArrayList<>();
		cible = new ArrayList<ClipboardListener>();
	}
	
	public void copyToClipboard(List<Clip> clips) {
		for (Clip c: clips) {
			this.clips.add(c.copy());
		}
		for (ClipboardListener listener: cible)
			listener.clipboardChanged();
	}
	
	public List<Clip> copyFromClipboard(){
		List<Clip> copy = new ArrayList<>();
		for (Clip c: clips) {
			copy.add(c.copy());
		}
		return copy; 
	}
	
	public void clear() {
		clips.clear(); 
		for (ClipboardListener listener: cible)
			listener.clipboardChanged();
	}
	
	public boolean isEmpty() {
		return clips.isEmpty();
	}
	
	public static Clipboard getInstance() {
		return clipboard;
	}
	
	public void addListener(ClipboardListener listener) {
		cible.add(listener);
	}
	
	public void removeListener(ClipboardListener listener) {
		cible.remove(listener);
	}
}