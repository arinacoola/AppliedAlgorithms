from flask import Flask, request, send_file
import matplotlib
matplotlib.use('Agg')
import networkx as nx
import matplotlib.pyplot as plt
import io
app = Flask(__name__)

@app.route('/draw', methods=['POST'])
def draw_graph():
    try:
        data = request.get_json(force=True)
        edges = data.get("edges", [])
        directed = data.get("directed", False)
        weighted = data.get("weighted", False)
        print("Received:", data)
        G = nx.DiGraph() if directed else nx.Graph()
        for edge in edges:
            if weighted:
                if len(edge) != 3:
                    raise ValueError("Weighted edge must have 3 elements")
                u, v, w = edge
                G.add_edge(u, v, weight=w)
            else:
                if len(edge) != 2:
                    raise ValueError("Unweighted edge must have 2 elements")
                u, v = edge
                G.add_edge(u, v)
        pos = nx.spring_layout(G)
        plt.figure(figsize=(10, 10))
        nx.draw(
            G, pos, with_labels=True,
            node_color='#f7c6e0', edge_color='#b19cd9',
            node_size=1500, font_size=20, font_color='#6a0dad'
        )
        if weighted:
            labels = nx.get_edge_attributes(G, 'weight')
            nx.draw_networkx_edge_labels(G, pos, edge_labels=labels, font_color='#6a0dad')
        buf = io.BytesIO()
        plt.savefig(buf, format='png')
        buf.seek(0)
        return send_file(buf, mimetype='image/png')
    except Exception as e:
        print("Error:", e)
        return {"error": str(e)}, 400
if __name__ == '__main__':
    app.run(port=5000)
