scoreboard players operation @s hold_value = @s oxygen_level
scoreboard players operation @s hold_value *= calc 100
scoreboard players operation @s hold_value /= @s oxygen_max
scoreboard players operation @s oxygen_percent = @s hold_value