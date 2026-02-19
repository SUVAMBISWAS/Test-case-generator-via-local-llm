$body = @{
    model = "gemma3:1b"
    prompt = "Hello, respond with 'System Online'"
    stream = $false
} | ConvertTo-Json

try {
    $response = Invoke-RestMethod -Uri "http://localhost:11434/api/generate" -Method Post -Body $body -ContentType "application/json" -ErrorAction Stop
    Write-Output "SUCCESS: Connected to Ollama"
    Write-Output "Model Response: $($response.response)"
} catch {
    Write-Error "FAILED: Could not connect to Ollama. Is it running?"
    Write-Error $_
}
