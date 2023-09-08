export interface SystemInfoPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
