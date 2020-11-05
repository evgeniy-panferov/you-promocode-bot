package ru.youpromocodebot.api.command;

public enum MainCommand {
    HELP("Помощь", "Помощь"),
    SEARCH_PARTNERSHIP("Найти магазин", "Найти магазин"),
    PARTNERSHIPS_LIST("Список партнеров", "Список партнеров");

    private final String button;
    private final String command;

    MainCommand(String button, String command) {
        this.button = button;
        this.command = command;
    }

    public String getButton() {
        return button;
    }

    public String getCommand() {
        return command;
    }
}
