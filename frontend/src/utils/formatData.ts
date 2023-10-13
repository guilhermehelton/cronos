export const formatDataToShow = (data?: Date | string): string => {
    if(data == undefined || data == '') {
        return '';
    }
    const dataString = data + '';
    const dataFormatada = dataString.slice(0, 10).split("-");
    const dia = dataFormatada[2];
    const mes = dataFormatada[1];
    const ano = dataFormatada[0];

    return `${dia}/${mes}/${ano}`;
}