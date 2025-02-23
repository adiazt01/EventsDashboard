package com.example.eventsdashboard.repositories;

import com.example.eventsdashboard.common.DatabaseConnection;
import com.example.eventsdashboard.models.Event;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventRepository {
    private final Connection connection;

    public EventRepository() {
        this.connection = DatabaseConnection.getInstance().getConnection();
        createTable();
    }

    private void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS events (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, date TEXT NOT NULL, time TEXT NOT NULL, location TEXT NOT NULL, organizer TEXT NOT NULL)";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public List<Event> findAll() {
        List<Event> events = new ArrayList<>();

        String sql = "SELECT * FROM events";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Event event = new Event();
                event.setId(rs.getInt("id"));
                event.setName(rs.getString("name"));
                event.setDate(rs.getString("date"));
                event.setTime(rs.getString("time"));
                event.setLocation(rs.getString("location"));
                event.setOrganizer(rs.getString("organizer"));
                events.add(event);
            }
        } catch (SQLException error) {
            error.printStackTrace();
        }
        return events;
    }

    public void create(Event event) {
        String sql = "INSERT INTO events (name, date, time, location, organizer) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, event.getName());
            statement.setString(2, event.getDate());
            statement.setString(3, event.getTime());
            statement.setString(4, event.getLocation());
            statement.setString(5, event.getOrganizer());
            statement.executeUpdate();
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public void update(Event event) {
        String sql = "UPDATE events SET name = ?, date = ?, time = ?, location = ?, organizer = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, event.getName());
            statement.setString(2, event.getDate());
            statement.setString(3, event.getTime());
            statement.setString(4, event.getLocation());
            statement.setString(5, event.getOrganizer());
            statement.setInt(6, event.getId());
            statement.executeUpdate();
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }


    public void delete(int id) {
        String sql = "DELETE FROM events WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }


    public void deleteAll() {
        String sql = "DELETE FROM events";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }
}
