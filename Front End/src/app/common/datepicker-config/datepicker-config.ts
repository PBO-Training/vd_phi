export class DateConfig {
    minDate = {year: 1900, month: 1, day: 1};
    maxDate = {year: 2099, month: 12, day: 31};
}
export function parseNewDateJs(date: Date): string {
    const month = ('' + (date.getMonth() + 1)).length < 2 ? '0' + (date.getMonth() + 1) : '' + (date.getMonth() + 1);
    const day = ('' + date.getDate()).length < 2 ? '0' + date.getDate() : '' + date.getDate();
    const year = date.getFullYear();
    return [year, month, day].join('-');
}
export function formatDate( dateTime: string): string  {
    // parse date yyyy-mm-dd to dd-mm-yyyy
    if (!dateTime) { return null; }
    const date = dateTime.split('-').reverse();
    const day = Number(date[0]);
    const month = Number(date[1]);
    const year = Number(date[2]);
    return [day, month, year].join('-');
}
export function parseDate(date: string) {
    // parse date format dd-mm-yyyy to SQL DATE yyyy-mm-ddT00:00:00
    if (!date) {
        return '';
    }
    const dateTime =  date.split('-');
    const day = Number(dateTime[0]) > 9 ? dateTime[0] : '0' + dateTime[0];
    const month = Number(dateTime[1]) > 9 ? dateTime[1] : '0' + dateTime[1];

    return [dateTime[2], month, day].join('-');
}
