execute store result score @s oxygen_storage run data get entity @s SelectedItem.tag.tank_lvl

execute if score @s oxygen_storage matches 1.. run function expansion:global/oxygen_regulation/refill
