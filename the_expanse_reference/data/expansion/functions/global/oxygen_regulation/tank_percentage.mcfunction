scoreboard players set @s tank_max 96000
scoreboard players operation @s hold_value_alt = @s oxygen_storage
scoreboard players operation @s hold_value_alt *= calc 100
scoreboard players operation @s hold_value_alt /= @s tank_max
scoreboard players operation @s tank_percent = @s hold_value_alt