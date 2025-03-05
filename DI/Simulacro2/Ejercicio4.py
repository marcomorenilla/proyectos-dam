import pandas as  pd
import altair as alt
import datapane as dp

df = pd.read_csv("informe.csv")
print(df)

df_avg = df.groupby(['Ciudad'])["Temperatura Media(ºC)"].mean().reset_index()
print(df_avg)

bar_chart = alt.Chart(df_avg).mark_bar().encode(
    x="Ciudad:N", y="Temperatura Media(ºC):Q", color="Ciudad:N"
).properties(title="Temperatura Media por ciudad")

line_chart = alt.Chart(df).mark_line().encode(
    x="Mes:N", y="Temperatura Media(ºC):Q", color="Ciudad:N"
).properties(title="Temperatura Media por ciudad")

tabla =  dp.DataTable(df)

report = dp.Report(
    bar_chart,
    line_chart,
    tabla
)

report.save("report.html")