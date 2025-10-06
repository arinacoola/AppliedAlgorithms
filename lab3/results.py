import pandas as pd
import matplotlib.pyplot as plt

df = pd.read_csv("graph_results.csv", sep=r"\s+|,|;", engine="python")
df.columns = [c.strip() for c in df.columns]

df["p"] = df["p"].astype(str).str.replace(",", ".")
df["p"] = df["p"].str.extract(r"([0-9]*\.?[0-9]+)").astype(float)

df = df.sort_values(["p", "n"])
unique_ps = sorted(df["p"].unique())

for p in unique_ps:
    sub = df[df["p"] == p]
    plt.figure(figsize=(9, 6))
    plt.plot(sub["n"], sub["DFS_M"], marker="o", linewidth=2, label="DFS (matrix)")
    plt.plot(sub["n"], sub["DFS_L"], marker="s", linewidth=2, label="DFS (list)")
    plt.plot(sub["n"], sub["Warshall"], marker="^", linewidth=2, label="Warshall")

    plt.title(f"Comparison of algorithms at p = {p}")
    plt.xlabel("Number of vertices (n)")
    plt.ylabel("Average execution time")
    plt.grid(True, linestyle="--", alpha=0.6)
    plt.legend()
    plt.tight_layout()
    plt.show()
