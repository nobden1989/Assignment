package assignment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import graphs.CC;
import graphs.DepthFirstOrder;
import graphs.Digraph;
import graphs.DijkstraSP;
import graphs.DirectedEdge;
import graphs.Edge;
import graphs.EdgeWeightedDigraph;
import graphs.EdgeWeightedGraph;
import graphs.Graph;
import graphs.In;
import graphs.KruskalMST;
import graphs.StdOut;
import graphs.SymbolGraph;
import util.FileHelper;

public class Assignment {

	public static void main(String[] args) {
		// Assignment02();
		// Assignment03();
		Assignment04_a();
		// Assignment04_b();
	}

	private static void Assignment02() {

		In in = new In(System.getProperty("user.dir") + "\\data\\largeDG.txt");
		StdOut.println("Loading graph.");
		Digraph G = new Digraph(in);

		StdOut.println("Prepairing data.");
		long startTime = System.currentTimeMillis();
		DepthFirstOrder dfs = new DepthFirstOrder(G);
		StdOut.println("Prepairing CPU time:" + (System.currentTimeMillis() - startTime) + "ms");

		StringBuffer sb = new StringBuffer();
		int lineLen = 1;
		StdOut.println("Start Preorder:");
		startTime = System.currentTimeMillis();
		for (int v : dfs.pre()) {
		}
		StdOut.println("Preorder CPU time:" + (System.currentTimeMillis() - startTime) + "ms");

		StdOut.println("Write output file largeDGPreorder.txt.");
		for (int v : dfs.pre()) {
			sb.append(v + ",");
			if (lineLen++ % 200 == 0) {
				sb.append("\n");
			}
		}

		try {
			FileHelper.writeFile(sb,
					System.getProperty("user.dir") + "\\data\\largeDGPreorder.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		StdOut.println("End of Preorder.");
		StdOut.println();

		sb.setLength(0);
		lineLen = 1;
		StdOut.println("Start Postorder:");
		startTime = System.currentTimeMillis();
		for (int v : dfs.post()) {
		}
		StdOut.println("Postorder CPU time:" + (System.currentTimeMillis() - startTime) + "ms");
		StdOut.println("Write output file largeDGPostorder.txt.");
		for (int v : dfs.post()) {
			sb.append(v + ",");
			if (lineLen++ % 200 == 0) {
				sb.append("\n");
			}
		}
		try {
			FileHelper.writeFile(sb,
					System.getProperty("user.dir") + "\\data\\largeDGPostorder.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}

		StdOut.println("End of Prostorder.");
	}

	private static void Assignment03() {

		In in = new In(System.getProperty("user.dir") + "\\data\\largeEWG.txt");
		EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);
		int s = 0;
		long startTime = System.currentTimeMillis();
		DijkstraSP sp = new DijkstraSP(G, s);
		StdOut.println("Constructing SP Time: " + (System.currentTimeMillis() - startTime) + " ms");
		StringBuffer sb = new StringBuffer();

		for (int t = 0; t < G.V(); t++) {
			if (sp.hasPathTo(t)) {
				sb.append(String.format("%d to %d (%.2f)  ", s, t, sp.distTo(t)));
				if (sp.hasPathTo(t)) {
					for (DirectedEdge e : sp.pathTo(t)) {
						sb.append(e + "   ");
					}
				}
				sb.append("\n");
			} else {
				sb.append(String.format("%d to %d         no path\n", s, t));
			}
		}

		try {
			FileHelper.writeFile(sb,
					System.getProperty("user.dir") + "\\data\\shortestPathEWG.txt");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		StdOut.println("SP ENDS");

		sb.setLength(0);

		In in2 = new In(System.getProperty("user.dir") + "\\data\\largeEWG.txt");
		EdgeWeightedGraph G2 = new EdgeWeightedGraph(in2);

		startTime = System.currentTimeMillis();
		KruskalMST mst = new KruskalMST(G2);
		StdOut.println(
				"Construct KruskalMST Time: " + (System.currentTimeMillis() - startTime) + " ms");
		for (Edge e : mst.edges()) {
			sb.append(e).append(" \n");
		}
		sb.append(String.format("%.5f", mst.weight()));
		try {
			FileHelper.writeFile(sb, System.getProperty("user.dir") + "\\data\\MSTEWG.txt");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		StdOut.println("MST ENDS");

	}

	private static void Assignment04_a() {
		SymbolGraph sg = new SymbolGraph(System.getProperty("user.dir") + "\\data\\movies.txt",
				"/");
		Graph G = sg.G();
		
		StdOut.println("Start CC");
		long startTime = System.currentTimeMillis();
		CC cc = new CC(G);
		StdOut.println("Construction and finding time: " + (System.currentTimeMillis() - startTime)
				+ " ms");
		// number of connected components
		
		int M = cc.count();
		StdOut.println(M + " components");

	}

	private static void Assignment04_b() {
		SymbolGraph sg = new SymbolGraph(System.getProperty("user.dir") + "\\data\\movies.txt",
				"/");

		StringBuffer sb = new StringBuffer();
		sb.append(findMatch(sg, "DiCaprio, Leonardo"));
		sb.append(findMatch(sg, "Roberts, Julia (I)"));
		sb.append(findMatch(sg, "Grant, Hugh (I)"));
		sb.append(findMatch_1(sg, new String[] { "Roberts, Julia (I)", "Grant, Hugh (I)" }));
		sb.append(findMatch_2(sg, new String[] { "Roberts, Julia (I)", "Grant, Hugh (I)" }));

		try {
			FileHelper.writeFile(sb, System.getProperty("user.dir") + "\\data\\moviesResult.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static String findMatch(SymbolGraph sg, String name) {
		if (sg.contains(name)) {
			StringBuilder sb = new StringBuilder();
			int s = sg.index(name);
			sb.append("The movie(s) starred by ").append(name).append(" are:\n");
			for (int v : sg.G().adj(s)) {
				sb.append("    ").append(sg.name(v)).append("\n");
			}
			return sb.toString();

		} else {
			return "Unincluded star names exist, result is empty.\n";
		}
	}

	private static String findMatch_1(SymbolGraph sg, String[] names) {
		Map<String, List<String>> movieMap = new HashMap<String, List<String>>();
		for (String name : names) {
			if (sg.contains(name)) {
				if (!movieMap.containsKey(name)) {
					movieMap.put(name, new ArrayList<String>());
				}

				int s = sg.index(name);
				for (int v : sg.G().adj(s)) {
					movieMap.get(name).add(sg.name(v));
				}

			} else {
				return "Unincluded star names exist, result is empty.\n";
			}
		}

		boolean isFirst = true;
		List<String> resultList = new ArrayList<String>();

		for (String name : movieMap.keySet()) {
			if (isFirst) {
				resultList.addAll(movieMap.get(name));
				isFirst = false;
			} else {
				List<String> cmpList = new ArrayList<String>();
				for (String movieName : movieMap.get(name)) {
					if (resultList.contains(movieName)) {
						cmpList.add(movieName);
					}
				}
				resultList = new ArrayList<String>();
				resultList.addAll(cmpList);
			}
		}

		StringBuilder sb = new StringBuilder();
		sb.append("The movie(s) starred by ");
		for (String starName : names) {
			sb.append(starName).append("and");
		}
		sb.setLength(sb.length() - 1);
		sb.append(" are:\n");
		for (String name : resultList) {
			sb.append("    ").append(name).append("\n");
		}
		return sb.toString();
	}

	private static String findMatch_2(SymbolGraph sg, String[] names) {

		List<String> resultList = new ArrayList<String>();

		CC cc = new CC(sg.G());
		boolean hasResult = true;
		boolean isFirst = true;
		String tmpName = "";
		for (String name : names) {
			if (isFirst) {
				tmpName = name;
				isFirst = false;
			} else {
				if (sg.contains(name) && sg.contains(tmpName)) {
					hasResult = hasResult && cc.connected(sg.index(name), sg.index(tmpName));
					tmpName = name;
				} else {
					return "Unincluded star names exist, result is empty.\n";
				}
			}
		}

		if (hasResult) {
			for (int v : sg.G().adj(sg.index(names[0]))) {
				for (int vn : sg.G().adj(v)) {
					for (String name : names) {
						if (!name.equals(names[0]) && name.equals(sg.name(vn))) {
							resultList.add(sg.name(v));
						}
					}
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		sb.append("The movie(s) starred by ");
		for (String starName : names) {
			sb.append(starName).append(" and ");
		}
		sb.setLength(sb.length() - 1);
		sb.append(" are:\n");
		for (String name : resultList) {
			sb.append("    ").append(name).append("\n");
		}
		return sb.toString();
	}

}
