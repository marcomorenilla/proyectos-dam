import pandas as pd
import altair as alt
import datapane as dp

df = pd.read_csv("informe.csv")
print("Información df",df.info(), "df", df,sep="\n")

def show_report(df):

    df_product = df.groupby("Producto")["Unidades Vendidas"].sum().reset_index()
    bar_chart = alt.Chart(df_product).mark_bar().encode(
        x="Producto:N", y="Unidades Vendidas:Q",color = "Producto:N"
    ).properties(title="Unidades Vendidas")

    df_month = df.groupby("Mes")["Unidades Vendidas"].sum().reset_index()
    print("df_agrupado",df_month, sep ="\n")
    line_chart = alt.Chart(df_month).mark_line().encode(
        x="Mes:N", y="Unidades Vendidas:Q",
    ).properties(title="Evolución de ventas")

    title = dp.HTML("<h1> Reporte examen DI </h1>")
    report = dp.Report(
        title,
        bar_chart,
        line_chart,
    )

    report.save("report.html")


if __name__ == '__main__':
    show_report(df)